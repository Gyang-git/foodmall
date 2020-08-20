package com.atghy.foodmall.order.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.OrderConstant;
import com.atghy.foodmall.common.constant.OrderCreateEnumConstant;
import com.atghy.foodmall.common.constant.OrderStatusEnumConstant;
import com.atghy.foodmall.common.exception.NoStockException;
import com.atghy.foodmall.common.to.mq.OrderTo;
import com.atghy.foodmall.common.to.mq.SeckillOrderTo;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import com.atghy.foodmall.common.vo.singleVo;
import com.atghy.foodmall.order.constant.PayWayEnumConstant;
import com.atghy.foodmall.order.entity.OrderInfoEntity;
import com.atghy.foodmall.order.entity.OrderItemEntity;
import com.atghy.foodmall.order.entity.PaymentInfoEntity;
import com.atghy.foodmall.order.feign.CartFeignService;
import com.atghy.foodmall.order.feign.CouponFeignService;
import com.atghy.foodmall.order.feign.FoodFeignService;
import com.atghy.foodmall.order.feign.TakeoutFeignService;
import com.atghy.foodmall.order.interceptor.LoginUserInterceptor;
import com.atghy.foodmall.order.service.OrderInfoService;
import com.atghy.foodmall.order.service.OrderItemService;
import com.atghy.foodmall.order.service.PaymentInfoService;
import com.atghy.foodmall.order.to.OrderCreateTo;
import com.atghy.foodmall.order.to.PayAsyncVo;
import com.atghy.foodmall.order.vo.*;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


@Slf4j
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    private ThreadLocal<OrderSubmitVo> submitVoThreadLocal = new InheritableThreadLocal<>();

    private final BigDecimal SINGLE_PACK_AMOUNT = new BigDecimal("0.45");

    private final BigDecimal SINGLE_ORDER_AMOUNT = new BigDecimal("3.00");

    private final BigDecimal SINGLE_SUCCESS_SCORE = new BigDecimal("5.80");

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    FoodFeignService foodFeignService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    TakeoutFeignService takeoutFeignService;

    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    PaymentInfoService paymentInfoServicel;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;
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
    public OrderConfirmVo confirmOrder(){
        log.info(Thread.currentThread().getId() + "<---方法执行前线程为");
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(requestAttributes);
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
        /**
         * 查询可选地址
         */
        List<AddressVo> address = takeoutFeignService.getAddress();
        confirmVo.setAddressList(address);
        //防重令牌
        String token = UUID.randomUUID().toString().replace("_", "");
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + customerResponseVo.getUuid(),token,30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        log.info(Thread.currentThread().getId() + "<---方法执行后线程为");
        return confirmVo;
    }

    /**
     * 提交订单功能
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        SubmitOrderResponseVo response = new SubmitOrderResponseVo();
        //更新状态码
        response.setCode(OrderCreateEnumConstant.ORDER_SUBMITING.getCode());
        log.info(OrderCreateEnumConstant.ORDER_SUBMITING.getMsg());
        submitVoThreadLocal.set(vo);
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        //1-验证令牌
        //redis脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();
        //原子验证令牌和删除令牌
        Long execute = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList(OrderConstant.USER_ORDER_TOKEN_PREFIX + customerResponseVo.getUuid()), orderToken);
        if (execute == 0L){
            //令牌验证失败
            response.setCode(1);
            return response;
        }else{
            //令牌验证成功
            //1-创建订单
            OrderCreateTo order = createOrder();
            //2-验证价格
            BigDecimal payAmout = order.getOrder().getPayAmout();
            BigDecimal payPrice = vo.getPayPrice();
            if (Math.abs(payAmout.subtract(payPrice).doubleValue()) < 0.01) {
                //金额对比成功 保存订单
                saveOrder(order);
                //3-锁定库存
                WareSkuLockVo wareSkuLockVo = new WareSkuLockVo();
                wareSkuLockVo.setOrderSn(order.getOrder().getOrderSn());
                List<OrderItemVo> locks = order.getItems().stream().map(item -> {
                    OrderItemVo orderItemVo = new OrderItemVo();
                    orderItemVo.setSkuId(item.getFoodId());
                    BeanUtils.copyProperties(item,orderItemVo);
                    return orderItemVo;
                }).collect(Collectors.toList());
                wareSkuLockVo.setLocks(locks);
                //远程锁定库存 若出现异常 则直接回滚
                R r = foodFeignService.orderLockStock(wareSkuLockVo);
                if (r.getCode() == 0){
                    //库存锁定成功
                    response.setOrder(order.getOrder());
                    //订单创建完成 发送消息队列 当超时未支付则关闭订单
                    rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",order.getOrder());
                    response.setCode(OrderCreateEnumConstant.ORDER_SUCCESS.getCode());
                    return response;
                }else{
                    //库存锁定失败
                    String msg = (String) r.get("msg");
                    throw new NoStockException(msg);
                }
            }else{
                response.setCode(OrderCreateEnumConstant.ORDER_FAIR_PRICE_CHANGE.getCode());
                return response;
            }
        }
    }

    /**
     * 关闭过期订单等
     * @param entity
     */
    @Override
    public void closeOrder(OrderEntity entity) {
        //查询订单状态
        OrderEntity orderEntity = this.getById(entity.getId());
        if (orderEntity.getOrderStatus() == OrderStatusEnumConstant.ORDER_WAIT_PAY.getCode()){
            //订单未付款 超时
            OrderEntity update = new OrderEntity();
            update.setId(orderEntity.getId());
            update.setOrderStatus(OrderStatusEnumConstant.ORDER_CANCEL.getCode());
            this.updateById(update);
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(orderEntity,orderTo);
            try {
                //订单取消消息 库存监听解锁库存
                rabbitTemplate.convertAndSend("order-event-exchange","order.release.other",orderTo);
            } catch (AmqpException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public FareInfoVo getOrderInfo(Long countTotal) {
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        Long uuid = customerResponseVo.getUuid();
        R r = couponFeignService.getDiscountByUuid(uuid);
        BigDecimal discount = r.getData("discount", new TypeReference<BigDecimal>() {
        });
        BigDecimal serviceFare = SINGLE_PACK_AMOUNT.multiply(BigDecimal.valueOf(countTotal));
        serviceFare = serviceFare.add(SINGLE_ORDER_AMOUNT);
        FareInfoVo fareInfoVo = new FareInfoVo();
        fareInfoVo.setDiscount(discount);
        fareInfoVo.setServiceFare(serviceFare);
        return fareInfoVo;
    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        PayVo payVo = new PayVo();
        OrderEntity order = this.getOrderByOrderSn(orderSn);
        //将金额格式化
        BigDecimal bigDecimal = order.getPayAmout().setScale(2, BigDecimal.ROUND_HALF_UP);
        payVo.setTotal_amount(bigDecimal.toString());
        payVo.setOut_trade_no(orderSn);
        List<OrderItemEntity> entityList = orderItemService.list(new QueryWrapper<OrderItemEntity>().eq("order_sn", orderSn));
        OrderItemEntity entity = entityList.get(0);
        payVo.setSubject(entity.getName());
        payVo.setBody("校园外卖自营网-安全绿色保障");
        return payVo;
    }

    @Override
    public OrderEntity getOrderByOrderSn(String orderSn) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn", orderSn);
        OrderEntity orderEntity = this.getOne(queryWrapper);
        return orderEntity;
    }

    @Override
    public PageUtils queryPageWithItem(Map<String, Object> params) {
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>().eq("uuid", customerResponseVo.getUuid()).orderByDesc("id")
        );
        List<OrderEntity> collect = page.getRecords().stream().map(order -> {
            QueryWrapper<OrderItemEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("order_sn", order.getOrderSn());
            List<OrderItemEntity> itemEntityList = orderItemService.list(wrapper);
            order.setItemEntities(itemEntityList);
            return order;
        }).collect(Collectors.toList());
        page.setRecords(collect);
        return new PageUtils(page);
    }

    @Override
    public String handPayResult(PayAsyncVo vo) {
        //保存支付信息
        PaymentInfoEntity paymentInfoEntity = new PaymentInfoEntity();
        paymentInfoEntity.setAlipayTradeNo(vo.getTrade_no());
        paymentInfoEntity.setOrderSn(vo.getOut_trade_no());
        paymentInfoEntity.setPaymentStatus(vo.getTrade_status());
        paymentInfoEntity.setCallbackContent(vo.getNotify_id());
        paymentInfoEntity.setCreateTime(new Date());
        paymentInfoEntity.setStatus(0);
        paymentInfoServicel.save(paymentInfoEntity);

        //更新订单状态
        if (vo.getTrade_status().equals("TRADE_SUCCESS") || vo.getTrade_status().equals("TRADE_FINISHED")){
            //订单号
            String outTradeNo = vo.getOut_trade_no();
            String payWay = "aliPay";
            baseMapper.updateOrderStatus(outTradeNo,OrderStatusEnumConstant.ORDER_WAIT_DELIVERY.getCode(), PayWayEnumConstant.ALIPAY.getCode());
        }
        return "success";
    }

    @Override
    public void createSeckillOrder(SeckillOrderTo seckillOrderTo) {
        //保存秒杀订单
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderSn(seckillOrderTo.getOrderSn());
        orderEntity.setCustomerId(seckillOrderTo.getUuid());
        orderEntity.setOrderStatus(OrderCreateEnumConstant.ORDER_SUCCESS.getCode());
        orderEntity.setStatus(0);
        BigDecimal multiply = seckillOrderTo.getSeckillPrice().multiply(new BigDecimal("" + seckillOrderTo.getNum()));
        orderEntity.setPayAmout(multiply);
        this.save(orderEntity);
        //保存详情信息
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderSn(seckillOrderTo.getOrderSn());
        orderItemEntity.setTotalPrice(multiply);
        orderItemEntity.setPrice(seckillOrderTo.getSeckillPrice());
        orderItemEntity.setFoodType("single");
        orderItemEntity.setFoodId(seckillOrderTo.getFood_id());
        R single = foodFeignService.info(seckillOrderTo.getFood_id());
        singleVo singleVo = single.getData("single", new TypeReference<singleVo>() {
        });
        orderItemEntity.setImgUrl(singleVo.getImgUrl());
        orderItemEntity.setName(singleVo.getName());
        orderItemEntity.setStatus(0);
        orderItemService.save(orderItemEntity);
    }

    @Override
    @Transactional
    public Boolean takeoutOrder(String orderSn, String takeoutSn, Long healthId) {
        //更新订单状态
        OrderEntity orderEntity = new OrderEntity();
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn",orderSn);
        orderEntity.setTakeoutId(takeoutSn);
        orderEntity.setHealthId(healthId);
        orderEntity.setOrderStatus(OrderStatusEnumConstant.ORDER_DELIVING.getCode());
        int i = baseMapper.update(orderEntity, queryWrapper);
        if (i == 1){
            //保存订单详情
            OrderInfoEntity infoEntity = new OrderInfoEntity();
            infoEntity.setOrderSn(orderSn);
            infoEntity.setTakeSn(takeoutSn);
            infoEntity.setAddress("校园外卖-配送处");
            infoEntity.setTime(new Date());
            infoEntity.setInfo("餐饮防疫检测完毕 已分配配送员开始派送! 预计15~30分钟配送到位");
            infoEntity.setStatus(0);
            orderInfoService.save(infoEntity);
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public boolean takeoutArrive(String orderSn, String takeSn) {
        //更新订单详情信息
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        orderInfoEntity.setOrderSn(orderSn);
        orderInfoEntity.setTakeSn(takeSn);
        orderInfoEntity.setTime(new Date());
        orderInfoEntity.setAddress("目的地");
        orderInfoEntity.setInfo("餐品已经送达目的地 系统自动签收 尽快完善评价售后噢~");
        orderInfoEntity.setStatus(0);
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn",orderSn);
        orderInfoService.save(orderInfoEntity);

        //更新订单状态
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(OrderStatusEnumConstant.ORDER_WAIT_FEEDBACK.getCode());
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("order_sn",orderSn);
        baseMapper.update(orderEntity,wrapper);
        return true;
    }

    private void saveOrder(OrderCreateTo order) {
        //保存订单
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        OrderEntity orderEntity = order.getOrder();
        orderEntity.setUuid(customerResponseVo.getUuid());
        this.save(orderEntity);
        //保存订单所属餐品
        List<OrderItemEntity> orderItems = order.getItems();
        orderItemService.saveBatch(orderItems);
    }

    /**
     * 创建订单
     * @return
     */
    private OrderCreateTo createOrder() {
        OrderCreateTo orderCreateTo = new OrderCreateTo();
        //1-生成订单号
        String orderSn = IdWorker.getTimeId();
        //2-构建订单信息
        OrderEntity orderEntity = buildOrder(orderSn);

        //所有订单
        List<OrderItemEntity> itemEntities = buildOrderItems(orderSn);

        //3-计算相关价格
        computePrice(orderEntity,itemEntities);

        orderCreateTo.setOrder(orderEntity);
        orderCreateTo.setItems(itemEntities);
        return orderCreateTo;
    }

    /**
     * 计算该订单相关价格信息
     * @param orderEntity
     * @param itemEntities
     */
    private void computePrice(OrderEntity orderEntity, List<OrderItemEntity> itemEntities) {
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        Long uuid = customerResponseVo.getUuid();
        R r = couponFeignService.getDiscountByUuid(uuid);
        BigDecimal discount = r.getData("discount", new TypeReference<BigDecimal>() {
        });
        //1-订单总额=每个订单项总额
        BigDecimal total = new BigDecimal("0.0");   //总价
        BigDecimal coupon = new BigDecimal("0.0");  //优惠
        BigDecimal integration = new BigDecimal("0.0"); //反馈积分
        BigDecimal service = new BigDecimal("0.0"); //服务费 = 打包费用 + 配送费用
        int itemCount = 0; //总单项数量
        //TODO 完善全场促销活动 新增促销表 对比时间期间 进行价格打折 时候只能select
        for (OrderItemEntity itemEntity : itemEntities) {
            total = total.add(itemEntity.getTotalPrice());//每个餐品的总价格 该价格未优惠
            itemCount = itemCount + itemEntity.getCount();
        }
        integration = SINGLE_SUCCESS_SCORE.multiply(BigDecimal.valueOf(itemCount));
        service = service.add(SINGLE_PACK_AMOUNT.multiply(BigDecimal.valueOf(itemCount))).add(SINGLE_ORDER_AMOUNT);
        orderEntity.setTotalAmout(total);
        orderEntity.setDiscountAmout(total.subtract(total.multiply(discount)));
        //优惠余额 = 总额 * 折扣
        coupon = total.multiply(discount);
        //实际付款 = 优惠 + 服务费service
        orderEntity.setPayAmout(coupon.add(service));
        orderEntity.setFreightAmout(service);
        orderEntity.setScore(integration);
    }

    private List<OrderItemEntity> buildOrderItems(String orderSn) {
        //再次确认购物项价格
        List<OrderItemVo> currentUserCartItems = cartFeignService.getCurrentUserCartItems();
        if (currentUserCartItems != null && currentUserCartItems.size()>0){
            List<OrderItemEntity> itemEntities = currentUserCartItems.stream().map(cartItem -> {
                OrderItemEntity orderItemEntity = buildOrderItem(cartItem);
                orderItemEntity.setOrderSn(orderSn);
                return orderItemEntity;
            }).collect(Collectors.toList());
            return itemEntities;
        }
        return null;
    }

    /**
     * 构建订单项
     * @param cartItem
     * @return
     */
    private OrderItemEntity buildOrderItem(OrderItemVo cartItem) {
        OrderItemEntity itemEntity = new OrderItemEntity();
        //1-餐品信息
        itemEntity.setFoodId(cartItem.getSkuId());
        itemEntity.setCount(cartItem.getCount());
        //2-餐品类型
        R r = foodFeignService.getSingleByName(cartItem.getName());
        if (r.getCode() == 0){
            itemEntity.setFoodType("single");
        }else {
            itemEntity.setFoodType("setmeal");
        }
        itemEntity.setImgUrl(cartItem.getImgUrl());
        itemEntity.setCount(cartItem.getCount());
        itemEntity.setName(cartItem.getName());
        itemEntity.setStatus(0);
        itemEntity.setPrice(cartItem.getPrice());
        itemEntity.setId(cartItem.getSkuId());
        itemEntity.setFoodId(cartItem.getSkuId());
        itemEntity.setTotalPrice(cartItem.getTotalPrice());
        return itemEntity;
    }

    /**
     * 构建订单信息
     * @param orderSn
     * @return
     */
    private OrderEntity buildOrder(String orderSn) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderSn(orderSn);
        //获取用户
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        orderEntity.setCustomerId(customerResponseVo.getUuid());
        orderEntity.setTakeName(customerResponseVo.getUsername());
        orderEntity.setTakePhone(customerResponseVo.getMobile());
        //confirm提交过来的数据
        OrderSubmitVo orderSubmitVo = submitVoThreadLocal.get();
        //订单的派送地址
        //TODO 页面提交过来的地址数据需再次格式验证 防止软件提交
        orderEntity.setAddressFirst(orderSubmitVo.getFirstAddress());
        orderEntity.setAddressSecond(orderSubmitVo.getSecondAddress());
        orderEntity.setAddressThird(orderSubmitVo.getThirdAddress());
        //其他信息设置
        orderEntity.setNote(orderSubmitVo.getNote());
        //更改订单状态为待付款
        orderEntity.setOrderStatus(OrderStatusEnumConstant.ORDER_WAIT_PAY.getCode());
        orderEntity.setStatus(0);
        return orderEntity;
    }
}