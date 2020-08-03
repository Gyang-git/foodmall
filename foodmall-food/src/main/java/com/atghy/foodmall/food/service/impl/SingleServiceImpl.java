package com.atghy.foodmall.food.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.DBNameConstant;
import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.common.to.es.SkuEsModel;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.food.entity.*;
import com.atghy.foodmall.food.feign.MemberFeignService;
import com.atghy.foodmall.food.feign.SearchFeignService;
import com.atghy.foodmall.food.service.*;
import com.atghy.foodmall.food.vo.ManagerVo;
import com.atghy.foodmall.food.vo.SingleItemVo;
import com.atghy.foodmall.food.vo.SingleVo;
import com.atghy.foodmall.food.vo.SkuHasStockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.SingleDao;
import org.springframework.transaction.annotation.Transactional;


@Service("singleService")
public class SingleServiceImpl extends ServiceImpl<SingleDao, SingleEntity> implements SingleService {

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    NatureService natureService;

    @Autowired
    SingleRawService singleRawService;

    @Autowired
    RestaurantAgentService restaurantAgentService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SingleEntity> page = this.page(
                new Query<SingleEntity>().getPage(params),
                new QueryWrapper<SingleEntity>()
        );

        return new PageUtils(page);
    }

    //线程池异步编排
    @Override
    @Transactional
    public void addSingle(SingleVo singleVo) throws ExecutionException, InterruptedException {
        //1-新增单品信息
        CompletableFuture<Long> singleInfoFuture = CompletableFuture.supplyAsync(() -> {
            SingleEntity singleEntity = new SingleEntity();
            BeanUtils.copyProperties(singleVo, singleEntity);
            this.baseMapper.insert(singleEntity);
            Long singleId = singleEntity.getId();
            return singleId;
        }, executor);

        //2-关联特性信息
        CompletableFuture<Void> natureInfoFuture = singleInfoFuture.thenRunAsync(() -> {
            NatureEntity natureEntity = new NatureEntity();
            BeanUtils.copyProperties(singleVo.getSingleNatureForm(), natureEntity);
            natureEntity.setSingleName(singleVo.getName());
            natureService.save(natureEntity);
        }, executor);

        //3-关联原料信息
        CompletableFuture<Void> rawInfoFuture = singleInfoFuture.thenAcceptAsync((singleId) -> {
            SingleRawEntity singleRawEntity = new SingleRawEntity();
            BeanUtils.copyProperties(singleVo.getSingleRawForm(), singleRawEntity);
            singleRawEntity.setSingleId(singleId);
            singleRawService.save(singleRawEntity);
        }, executor);

        //4-关联店面代理信息
        CompletableFuture<Void> agentRestaurantFuture = singleInfoFuture.thenRunAsync(() -> {
            RestaurantAgentEntity restaurantAgentEntity = new RestaurantAgentEntity();
            Long restaurantId = restaurantService.getOne(new QueryWrapper<RestaurantEntity>().eq("name", singleVo.getRestaurantName())).getId();
            Long agentId = memberFeignService.getAgentIdByName(singleVo.getSingleRawForm().getAgentName());
            restaurantAgentEntity.setRestaurantId(restaurantId);
            restaurantAgentEntity.setAgentId(agentId);
            restaurantAgentEntity.setStatus(0);
            restaurantAgentService.save(restaurantAgentEntity);
        }, executor);
        CompletableFuture.allOf(singleInfoFuture, natureInfoFuture, rawInfoFuture, agentRestaurantFuture).get();
    }

    @Override
    public Long getSingleIdByName(String name) {
        SingleEntity entity = this.baseMapper.selectOne(new QueryWrapper<SingleEntity>().eq("name", name));
        return entity.getId();
    }

    @Override
    public Boolean upSingle(Long id) {
        //1-查出并封装当前singleId的所有信息
        SkuEsModel esModel = new SkuEsModel();
        SingleEntity singleEntity = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(singleEntity,esModel);
        esModel.setIsSingle((long) 1);
        esModel.setSingleId(id);
        //1--查询单品特性
        NatureEntity natureEntity = natureService.getNatureByName(singleEntity.getName());
        SkuEsModel.Nature nature = new SkuEsModel.Nature();
        BeanUtils.copyProperties(natureEntity,nature);
        esModel.setNature(nature);
        //2-检查所属饭店星级(若星级低于一星 则该单品不可上架）
        RestaurantEntity restaurantEntity = restaurantService.getOne(new QueryWrapper<RestaurantEntity>().eq("name", singleEntity.getRestaurantName()));
        if (restaurantEntity.getLevel() >= 1){
            //3-检查饭店营业执照及卫生执照
            R info = memberFeignService.getEntityById(restaurantEntity.getId());
            ManagerVo managerVo = info.getData("manager", new TypeReference<ManagerVo>() {
            });
            System.out.println(managerVo);
            if (managerVo.getBusineseImgUrl() == null || managerVo.getSanitationImgUrl() == null){
                log.error(BizCodeEnume.MANAGER_PERMIT_LACK_EXCEPTION.getMsg());
                return false;
            }else{
                //4-查询商品库存状态
                if (singleEntity.getQuantity() > singleEntity.getQuantityLock()){
                    esModel.setHasStock(true);
                    //5-将数据发送给es进行保存
                    R r = searchFeignService.foodStatusUp(esModel);
                    if (r.getCode() == 0){
                        //远程调用成功
                        //6-修改当前spu的状态
                        singleEntity.setUseStatus(1);
                        int i = baseMapper.updateById(singleEntity);
                        System.out.println(i);
                    }else {
                        log.error("远程调用food-search出错");
                        return false;
                    }
                }else {
                    log.error(BizCodeEnume.NO_STOCK_EXCEPTION.getMsg());
                    return false;
                }
            }
        }else {
            log.error(BizCodeEnume.RESTAURANT_LEVEL_TOOLOW_EXCEPTION.getMsg());
            return false;
        }
        return true;
    }

    //非异步编排
    @Override
    public SingleItemVo singleItem(Long singleId) {
        SingleItemVo singleItem = new SingleItemVo();
        //1-封装基本信息
        SingleEntity singleEntity = this.getById(singleId);
        singleItem.setSingle(singleEntity);
        //2-封装特性组合
        NatureEntity natureEntity = natureService.getNatureByName(singleEntity.getName());
        singleItem.setNatureEntity(natureEntity);
        //3-封装原料信息
        SingleRawEntity singleRawEntity = singleRawService.getOne(new QueryWrapper<SingleRawEntity>().eq("single_id", singleId));
        singleItem.setSingleRawEntity(singleRawEntity);
        //4-当前单品的秒杀信息
        return singleItem;
    }

    @Override
    public SingleEntity getSingleByName(String name) {
        QueryWrapper<SingleEntity> queryWrapper = new QueryWrapper<SingleEntity>().eq("name", name);
        SingleEntity entity = this.baseMapper.selectOne(queryWrapper);
        return entity;
    }

    @Override
    public List<SkuHasStockVo> getSingleHasStock(List<Long> singleIds) {
        List<SkuHasStockVo> collect = singleIds.stream().map(singleId -> {
            SkuHasStockVo vo = new SkuHasStockVo();
            Long count = baseMapper.getSkuStock(DBNameConstant.SINGLE_ENTITY_DB_NAME, singleId);
            vo.setSkuId(singleId);
            vo.setHasStock(count == null ? false : count > 0);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

//    //线程池非异步编排
//    @Override
//    @Transactional
//    public void addSingle(SingleVo singleVo) {
//        //1-新增单品信息
//        SingleEntity singleEntity = new SingleEntity();
//        BeanUtils.copyProperties(singleVo,singleEntity);
//        this.baseMapper.insert(singleEntity);
//        Long singleId = singleEntity.getId();
//        //2-关联特性信息
//        NatureEntity natureEntity = new NatureEntity();
//        BeanUtils.copyProperties(singleVo.getSingleNatureForm(),natureEntity);
//        natureEntity.setSingleName(singleVo.getName());
//        natureService.save(natureEntity);
//        //3-关联原料信息
//        SingleRawEntity singleRawEntity = new SingleRawEntity();
//        BeanUtils.copyProperties(singleVo.getSingleRawForm(),singleRawEntity);
//        singleRawEntity.setSingleId(singleId);
//        singleRawService.save(singleRawEntity);
//        //4-关联店面代理信息
//        RestaurantAgentEntity restaurantAgentEntity = new RestaurantAgentEntity();
//        Long restaurantId = restaurantService.getOne(new QueryWrapper<RestaurantEntity>().eq("name", singleVo.getRestaurantName())).getId();
//        Long agentId = memberFeignService.getAgentIdByName(singleVo.getSingleRawForm().getAgentName());
//        restaurantAgentEntity.setRestaurantId(restaurantId);
//        restaurantAgentEntity.setAgentId(agentId);
//        restaurantAgentEntity.setStatus(0);
//        restaurantAgentService.save(restaurantAgentEntity);
//    }
}