package com.atghy.foodmall.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.takeout.entity.AddressFirstEntity;

import java.util.Map;

/**
 * 记录外卖可选地址一级分类
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
public interface AddressFirstService extends IService<AddressFirstEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

