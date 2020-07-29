package com.atghy.foodmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.ware.entity.AgentRawInfoEntity;

import java.util.Map;

/**
 * 收集代理商供应原料信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
public interface AgentRawInfoService extends IService<AgentRawInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

