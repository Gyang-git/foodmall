package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.AgentEntity;

import java.util.Map;

/**
 * 收集供应商信息 外联供应商原料信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
public interface AgentService extends IService<AgentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Long getAgentIdByName(String name);
}

