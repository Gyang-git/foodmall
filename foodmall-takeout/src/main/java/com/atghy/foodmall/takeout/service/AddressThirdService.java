package com.atghy.foodmall.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.takeout.entity.AddressThirdEntity;

import java.util.Map;

/**
 * 外卖可选地址三级分类
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
public interface AddressThirdService extends IService<AddressThirdEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

