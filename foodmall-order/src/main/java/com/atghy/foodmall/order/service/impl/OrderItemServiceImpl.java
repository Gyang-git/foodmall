package com.atghy.foodmall.order.service.impl;

import com.atghy.foodmall.common.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.order.dao.OrderItemDao;
import com.atghy.foodmall.order.entity.OrderItemEntity;
import com.atghy.foodmall.order.service.OrderItemService;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderItemEntity> getItemByOrderSn(String orderSn) {
        QueryWrapper<OrderItemEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn",orderSn);
        List<OrderItemEntity> orderItemEntity = baseMapper.selectList(queryWrapper);
        return orderItemEntity;
    }

}