package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.TakemanEntity;

import java.util.Map;

/**
 * 收集外卖员个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
public interface TakemanService extends IService<TakemanEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

