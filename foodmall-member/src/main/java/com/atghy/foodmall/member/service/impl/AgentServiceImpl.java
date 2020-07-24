package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.AgentDao;
import com.atghy.foodmall.member.entity.AgentEntity;
import com.atghy.foodmall.member.service.AgentService;


@Service("agentService")
public class AgentServiceImpl extends ServiceImpl<AgentDao, AgentEntity> implements AgentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AgentEntity> page = this.page(
                new Query<AgentEntity>().getPage(params),
                new QueryWrapper<AgentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long getAgentIdByName(String name) {
        AgentEntity agentEntity = this.baseMapper.selectOne(new QueryWrapper<AgentEntity>().eq("name", name));
        Long agentEntityId = agentEntity.getId();
        return agentEntityId;
    }

}