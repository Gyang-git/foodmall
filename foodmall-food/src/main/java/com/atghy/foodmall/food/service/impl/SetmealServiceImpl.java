package com.atghy.foodmall.food.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.common.to.es.SkuEsModel;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.food.entity.RestaurantEntity;
import com.atghy.foodmall.food.entity.SingleSetmealEntity;
import com.atghy.foodmall.food.feign.MemberFeignService;
import com.atghy.foodmall.food.feign.SearchFeignService;
import com.atghy.foodmall.food.service.RestaurantService;
import com.atghy.foodmall.food.service.SingleService;
import com.atghy.foodmall.food.service.SingleSetmealService;
import com.atghy.foodmall.food.vo.ManagerVo;
import com.atghy.foodmall.food.vo.SetmealVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.SetmealDao;
import com.atghy.foodmall.food.entity.SetmealEntity;
import com.atghy.foodmall.food.service.SetmealService;
import org.springframework.transaction.annotation.Transactional;


@Service("setmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealDao, SetmealEntity> implements SetmealService {

    @Autowired
    SingleService singleService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    SearchFeignService searchFeignService;

    @Autowired
    SingleSetmealService singleSetmealService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SetmealEntity> page = this.page(
                new Query<SetmealEntity>().getPage(params),
                new QueryWrapper<SetmealEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 非异步编排
     * @param setmealVo
     */
    @Override
    @Transactional
    public void addSetmeal(SetmealVo setmealVo) {
        //1-新增基础数据
        SetmealEntity setmealEntity = new SetmealEntity();
        BeanUtils.copyProperties(setmealVo,setmealEntity);
        System.out.println(setmealVo.getPrice());
        System.out.println(setmealEntity.getPrice());
        int insert = this.baseMapper.insert(setmealEntity);
        Long setmealId = setmealEntity.getId();
        //2-关联单品数据
        List<String> comSingleNames = setmealVo.getSetMealCom().getSingleName();
        List<String> comSingleQuants = setmealVo.getSetMealCom().getSingleQuantity();
        for (int i = 0 ; i < setmealVo.getComNum(); i++ ){
            if(comSingleNames.get(i) != null && comSingleQuants.get(i) != null && new Integer(comSingleQuants.get(i)) != 0){
                Long singleId = singleService.getSingleIdByName(comSingleNames.get(i));
                SingleSetmealEntity singleSetmealEntity = new SingleSetmealEntity();
                singleSetmealEntity.setSingleId(singleId);
                singleSetmealEntity.setSetmealId(setmealId);
                singleSetmealEntity.setStatus(0);
                singleSetmealService.save(singleSetmealEntity);
            }
        }
    }

    @Override
    public Boolean upSetmeal(Long id) {
        //1-查出并封装当前SetmealId的所有信息
        SkuEsModel esModel = new SkuEsModel();
        SetmealEntity setmealEntity = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(setmealEntity,esModel);
        esModel.setIsSingle((long) 0);
        esModel.setSetmealId(id);
        //2-检查所属饭店星级(若星级低于一星 则该单品不可上架）
        RestaurantEntity restaurantEntity = restaurantService.getOne(new QueryWrapper<RestaurantEntity>().eq("name", setmealEntity.getRestaurantName()));
        if (restaurantEntity.getLevel() >=1 ){
            //3-检查饭店营业执照及卫生执照
//            R info = memberFeignService.
            R r = memberFeignService.getEntityById(restaurantEntity.getId());
            ManagerVo managerVo = r.getData("manager", new TypeReference<ManagerVo>() {
            });
            if (managerVo.getBusineseImgUrl() == null || managerVo.getSanitationImgUrl() == null){
                log.error(BizCodeEnume.MANAGER_PERMIT_LACK_EXCEPTION.getMsg());
                return false;
            }else {
                //4-查询套餐库存状态
                if (setmealEntity.getQuantity() > setmealEntity.getQuantityLock()){
                    esModel.setHasStock(true);
                    //5-将数据发送给es进行保存
                    R up = searchFeignService.foodStatusUp(esModel);
                    if (up.getCode() == 0){
                        //远程调用成功
                        //6-修改当前spu的状态
                        setmealEntity.setUseStatus(1);
                        int i = baseMapper.updateById(setmealEntity);
                        System.out.println(i);
                    }else {
                        log.error("远程调用出错");
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
}