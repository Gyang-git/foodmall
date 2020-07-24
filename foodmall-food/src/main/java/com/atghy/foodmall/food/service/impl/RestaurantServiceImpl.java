package com.atghy.foodmall.food.service.impl;

import com.atghy.foodmall.food.feign.MemberFeignService;
import com.atghy.foodmall.food.vo.ManagerForm;
import com.atghy.foodmall.food.vo.ManagerVo;
import com.atghy.foodmall.food.vo.RestaurantVo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.RestaurantDao;
import com.atghy.foodmall.food.entity.RestaurantEntity;
import com.atghy.foodmall.food.service.RestaurantService;
import org.springframework.transaction.annotation.Transactional;


@Service("restaurantService")
public class RestaurantServiceImpl extends ServiceImpl<RestaurantDao, RestaurantEntity> implements RestaurantService {

    @Autowired
    MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RestaurantEntity> page = this.page(
                new Query<RestaurantEntity>().getPage(params),
                new QueryWrapper<RestaurantEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void addRestaurant(RestaurantVo restaurantVo) {
        //增添店面基础信息
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        BeanUtils.copyProperties(restaurantVo,restaurantEntity);
        restaurantEntity.setStatus(0);
        restaurantEntity.setLevel(1);
        this.baseMapper.insert(restaurantEntity);
        Long restaurantId = restaurantEntity.getId();
        //关联负责人信息
        ManagerVo managerVo = new ManagerVo();
        ManagerForm managerForm = restaurantVo.getManagerForm();
        managerVo.setRestaurantId(restaurantId);
        managerVo.setName(restaurantVo.getManagerName());
        managerVo.setMobile(managerForm.getMobile());
        managerVo.setEmail(managerForm.getEmail());
        managerVo.setGender(managerForm.getGender());
        managerVo.setBusineseImgUrl(managerForm.getBusineseImgUrl());
        managerVo.setSanitationImgUrl(managerForm.getStanitationImgUrl());
        managerVo.setStatus(0);
        managerVo.setUpdateTime(new Date());
        memberFeignService.save(managerVo);
    }
}