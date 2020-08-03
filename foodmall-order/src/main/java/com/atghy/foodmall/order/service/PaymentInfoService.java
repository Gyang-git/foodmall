package com.atghy.foodmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

