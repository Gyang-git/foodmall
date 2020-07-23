package com.atghy.foodmall.food.service.impl;

import com.atghy.foodmall.food.entity.*;
import com.atghy.foodmall.food.feign.MemberFeignService;
import com.atghy.foodmall.food.service.*;
import com.atghy.foodmall.food.vo.SingleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.Map;
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
    NatureService natureService;

    @Autowired
    SingleRawService singleRawService;

    @Autowired
    RestaurantAgentService restaurantAgentService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SingleEntity> page = this.page(
                new Query<SingleEntity>().getPage(params),
                new QueryWrapper<SingleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void addSingle(SingleVo singleVo) {
        //1-新增单品信息
        SingleEntity singleEntity = new SingleEntity();
        BeanUtils.copyProperties(singleVo,singleEntity);
        this.baseMapper.insert(singleEntity);
        Long singleId = singleEntity.getId();
        //2-关联特性信息
        NatureEntity natureEntity = new NatureEntity();
        BeanUtils.copyProperties(singleVo.getSingleNatureForm(),natureEntity);
        natureEntity.setSingleName(singleVo.getName());
        natureService.save(natureEntity);
        //3-关联原料信息
        SingleRawEntity singleRawEntity = new SingleRawEntity();
        BeanUtils.copyProperties(singleVo.getSingleRawForm(),singleRawEntity);
        singleRawEntity.setSingleId(singleId);
        singleRawService.save(singleRawEntity);
        //4-关联店面代理信息
        RestaurantAgentEntity restaurantAgentEntity = new RestaurantAgentEntity();
        Long restaurantId = restaurantService.getOne(new QueryWrapper<RestaurantEntity>().eq("name", singleVo.getRestaurantName())).getId();
        Long agentId = memberFeignService.getAgentIdByName(singleVo.getSingleRawForm().getAgentName());
        restaurantAgentEntity.setRestaurantId(restaurantId);
        restaurantAgentEntity.setAgentId(agentId);
        restaurantAgentEntity.setStatus(0);
        restaurantAgentService.save(restaurantAgentEntity);
    }
}