package com.atghy.foodmall.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.order.dao.OrderInfoDao;
import com.atghy.foodmall.order.entity.OrderInfoEntity;
import com.atghy.foodmall.order.service.OrderInfoService;


@Service("orderInfoService")
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoEntity> implements OrderInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderInfoEntity> page = this.page(
                new Query<OrderInfoEntity>().getPage(params),
                new QueryWrapper<OrderInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderInfoEntity> getInfo(String orderSn) {
        QueryWrapper<OrderInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn",orderSn);
        List<OrderInfoEntity> entityList = baseMapper.selectList(queryWrapper);
        return entityList;
    }

}