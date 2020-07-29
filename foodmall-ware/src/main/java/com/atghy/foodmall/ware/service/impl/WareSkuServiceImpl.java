package com.atghy.foodmall.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.ware.dao.WareSkuDao;
import com.atghy.foodmall.ware.entity.WareSkuEntity;
import com.atghy.foodmall.ware.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WareSkuEntity> getRawsByAgentname(String agentName) {
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<WareSkuEntity>().eq("agent_name", agentName);
        List<WareSkuEntity> wareSkuEntities = this.baseMapper.selectList(queryWrapper);
        System.out.println(wareSkuEntities);
        return wareSkuEntities;
    }

}