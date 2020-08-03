package com.atghy.foodmall.order.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.OrderConstant;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import com.atghy.foodmall.order.feign.CartFeignService;
import com.atghy.foodmall.order.feign.FoodFeignService;
import com.atghy.foodmall.order.interceptor.LoginUserInterceptor;
import com.atghy.foodmall.order.vo.OrderConfirmVo;
import com.atghy.foodmall.order.vo.OrderItemVo;
import com.atghy.foodmall.order.vo.SkuStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.order.dao.OrderDao;
import com.atghy.foodmall.order.entity.OrderEntity;
import com.atghy.foodmall.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    FoodFeignService foodFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );
        return new PageUtils(page);
    }

    //TODO 异步实现
    @Override
    public OrderConfirmVo confirmOrder() {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();

        /**
         * 查询购物车所有的购物项
         * 检查库存状态
         */
        List<OrderItemVo> items = cartFeignService.getCurrentUserCartItems();
        confirmVo.setItems(items);
        //查询库存状态
        List<OrderItemVo> confirmItems = confirmVo.getItems();
        List<Long> collect = confirmItems.stream().map(item -> item.getSkuId()).collect(Collectors.toList());
        R r = foodFeignService.getSingleHasStock(collect);
        List<SkuStockVo> data = r.getData(new TypeReference<List<SkuStockVo>>() {
        });
        if (data != null){
            Map<Long, Boolean> map = data.stream().collect(Collectors.toMap(SkuStockVo::getSkuId, SkuStockVo::getHasStock));
            confirmVo.setStocks(map);
        }

        /**
         * 查询优惠信息 返回优惠折扣
         */
        Integer score = customerResponseVo.getScore();
        confirmVo.setScore(score);

        //防重令牌
        String token = UUID.randomUUID().toString().replace("_", "");
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + customerResponseVo.getUuid(),token,30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        return confirmVo;
    }

}