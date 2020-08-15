package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.UfsChefEntity;

import java.util.Map;

/**
 * 收集厨师个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:20:00
 */
public interface UfsChefService extends IService<UfsChefEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

