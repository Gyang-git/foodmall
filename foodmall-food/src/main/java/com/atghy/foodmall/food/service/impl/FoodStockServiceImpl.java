package com.atghy.foodmall.food.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.DBNameConstant;
import com.atghy.foodmall.common.constant.OrderStatusEnumConstant;
import com.atghy.foodmall.common.to.mq.OrderLockTo;
import com.atghy.foodmall.common.to.mq.OrderTo;
import com.atghy.foodmall.common.to.mq.StockLockedTo;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.food.dao.FoodStockDao;
import com.atghy.foodmall.food.entity.OrderLockEntity;
import com.atghy.foodmall.food.entity.SetmealEntity;
import com.atghy.foodmall.food.entity.SingleEntity;
import com.atghy.foodmall.food.feign.OrderFeignService;
import com.atghy.foodmall.food.service.FoodStockService;
import com.atghy.foodmall.food.service.OrderLockService;
import com.atghy.foodmall.food.service.SetmealService;
import com.atghy.foodmall.food.service.SingleService;
import com.atghy.foodmall.food.vo.OrderItemVo;
import com.atghy.foodmall.food.vo.OrderVo;
import com.atghy.foodmall.food.vo.WareSkuLockVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-07
 * Description:
 */
@Service
public class FoodStockServiceImpl implements FoodStockService {

    @Autowired
    SingleService singleService;

    @Autowired
    SetmealService setmealService;

    @Autowired
    OrderLockService orderLockService;

    @Autowired
    OrderFeignService orderFeignService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 为订单锁定库存
     * @param lockVo
     * @return
     */
    @Override
    @Transactional
    public Boolean orderLockStock(WareSkuLockVo lockVo) {
        List<OrderItemVo> orderItemVos = lockVo.getLocks();
        Date date = new Date();
        for (OrderItemVo orderItemVo : orderItemVos) {
            StockLockedTo stockLockedTo = new StockLockedTo();
            OrderLockTo orderLockTo = new OrderLockTo();
            OrderLockEntity orderLockEntity = new OrderLockEntity();
            String foodType = orderItemVo.getFoodType();
            if (foodType.equals("single")){
                SingleEntity food = singleService.getById(orderItemVo.getSkuId());
                //单品
                food.setQuantity(food.getQuantity() - orderItemVo.getCount());
                food.setQuantityLock(Long.valueOf(orderItemVo.getCount()));
                singleService.saveOrUpdate(food);
                orderLockTo.setType("single");
            }else if (foodType.equals("setmeal")){
                //套餐
                SetmealEntity setmealEntity = setmealService.getById(orderItemVo.getSkuId());
                setmealEntity.setQuantity(setmealEntity.getQuantity() - orderItemVo.getCount());
                setmealEntity.setQuantityLock(Long.valueOf(orderItemVo.getCount()));
                setmealService.saveOrUpdate(setmealEntity);
                orderLockTo.setType("setmeal");
            }
            stockLockedTo.setSkuId(orderItemVo.getSkuId());
            orderLockTo.setOrderSn(lockVo.getOrderSn());
            orderLockTo.setName(orderItemVo.getName());
            orderLockTo.setLockCount(orderItemVo.getCount());
            orderLockTo.setDatetime(date);
            orderLockTo.setLockStatus(1);
            stockLockedTo.setOrderLockTo(orderLockTo);
            BeanUtils.copyProperties(orderLockTo,orderLockEntity);
            orderLockEntity.setSkuId(orderItemVo.getSkuId());
            boolean save = orderLockService.save(orderLockEntity);
            if (save){
                //库存锁定成功 发送消息队列
                rabbitTemplate.convertAndSend("stock-event-exchange","stock.locked",stockLockedTo);
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 解锁库存
     * 查询数据库关于该订单的锁定库存信息
     * 1-锁定数量>0--库存锁定状态
     *      解锁:
     *          1--没有该订单 必须解锁
     *          2--有该订单
     *              1---订单已经取消 ： 解锁库存
     *              2---没有取消 ： 不能解锁
     *              3---订单完成 ： 无需解锁 扣库存
     * 2-锁定数量=0
     *      库存锁定失败 回滚
     * @param to
     */
    @Override
    public void unlockStock(StockLockedTo to) {
        OrderLockEntity lockEntity = new OrderLockEntity();
        Long id = lockEntity.getId();
        OrderLockEntity byId = orderLockService.getById(id);
        if (byId != null){
            //库存已经锁定 根据订单号查询订单状态
            String orderSn = lockEntity.getOrderSn();
            R r = orderFeignService.getOrderByOrderSn(orderSn);
            if (r.getCode() == 0){
                OrderVo order = r.getData("order", new TypeReference<OrderVo>() {
                });
                if (order == null || order.getOrderStatus() == OrderStatusEnumConstant.ORDER_CANCEL.getCode()){
                    //订单已不存在 || 订单已取消
                    if (lockEntity.getLockCount() > 0){
                        unLockStock(lockEntity.getSkuId(),lockEntity.getType(),lockEntity.getLockCount(),id);
                    }
                }
            }else{
                throw new RuntimeException("远程服务调用失败");
            }
        }else{
            //库存未锁定 无需操作
        }
    }

    /**
     * 订单关闭而解锁库存
     * @param orderTo
     */
    @Override
    @Transactional
    public void unlockStock(OrderTo orderTo) {
        Long orderSn = orderTo.getOrderSn();
        List<OrderLockEntity> orderLockList = orderLockService.getOrderLockByOrderSn(orderSn);
        for (OrderLockEntity orderLockEntity : orderLockList) {
            if (orderLockEntity.getLockStatus() == 1){
                //未解锁
                unLockStock(orderLockEntity.getSkuId(),orderLockEntity.getType(),orderLockEntity.getLockCount(),orderLockEntity.getId());
                orderLockEntity.setLockStatus(0);
                //更新解锁库存状态
                orderLockService.updateById(orderLockEntity);
            }
        }
    }

    /**
     * 正式解锁库存
     * @param skuId
     * @param type
     * @param lockCount
     */
    private void unLockStock(Long skuId, String type, Integer lockCount,Long orderLockId) {
        String DBName = "";
        if (type == "single"){
            DBName = DBNameConstant.SINGLE_ENTITY_DB_NAME;
        }else {
            DBName = DBNameConstant.SETMEAL_ENTITY_DB_NAME;
        }
        FoodStockDao.unLockStock(DBName,skuId,lockCount);
        //解锁完毕 更改锁定状态
        OrderLockEntity orderLockEntity = new OrderLockEntity();
        orderLockEntity.setId(orderLockId);
        orderLockEntity.setLockStatus(0);
        orderLockService.updateById(orderLockEntity);
    }
}
