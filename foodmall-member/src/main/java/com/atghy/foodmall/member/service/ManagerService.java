package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.ManagerEntity;

import java.util.Map;

/**
 * 收集餐饮负责人个人信息及饭店关联
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
public interface ManagerService extends IService<ManagerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ManagerEntity getEntityById(Long id);
}

