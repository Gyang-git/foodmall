package com.atghy.foodmall.food.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.OrderLockDao;
import com.atghy.foodmall.food.entity.OrderLockEntity;
import com.atghy.foodmall.food.service.OrderLockService;


@Service("orderLockService")
public class OrderLockServiceImpl extends ServiceImpl<OrderLockDao, OrderLockEntity> implements OrderLockService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderLockEntity> page = this.page(
                new Query<OrderLockEntity>().getPage(params),
                new QueryWrapper<OrderLockEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderLockEntity> getOrderLockByOrderSn(Long orderSn) {
        OrderLockDao orderLockDao = this.baseMapper;
        List<OrderLockEntity> entityList = orderLockDao.selectList(new QueryWrapper<OrderLockEntity>().eq("order_Sn", orderSn));
        return entityList;
    }
}