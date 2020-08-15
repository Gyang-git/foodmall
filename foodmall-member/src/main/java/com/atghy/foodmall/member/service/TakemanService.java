package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.TakemanEntity;

import java.util.Map;

/**
 * 收集派送员个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:26:58
 */
public interface TakemanService extends IService<TakemanEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

