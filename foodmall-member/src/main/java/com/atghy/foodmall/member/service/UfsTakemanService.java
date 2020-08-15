package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.UfsTakemanEntity;

import java.util.Map;

/**
 * 收集派送员个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:20:00
 */
public interface UfsTakemanService extends IService<UfsTakemanEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

