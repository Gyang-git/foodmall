package com.atghy.foodmall.member.service;

import com.atghy.foodmall.member.vo.CustomerLoginVo;
import com.atghy.foodmall.member.vo.CustomerRegistVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.CustomerEntity;

import java.util.Map;

/**
 * 收集顾客个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
public interface CustomerService extends IService<CustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(CustomerRegistVo vo);

    CustomerEntity login(CustomerLoginVo vo);
}

