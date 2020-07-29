package com.atghy.foodmall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.ware.dao.AgentRawInfoDao;
import com.atghy.foodmall.ware.entity.AgentRawInfoEntity;
import com.atghy.foodmall.ware.service.AgentRawInfoService;


@Service("agentRawInfoService")
public class AgentRawInfoServiceImpl extends ServiceImpl<AgentRawInfoDao, AgentRawInfoEntity> implements AgentRawInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AgentRawInfoEntity> page = this.page(
                new Query<AgentRawInfoEntity>().getPage(params),
                new QueryWrapper<AgentRawInfoEntity>()
        );

        return new PageUtils(page);
    }

}