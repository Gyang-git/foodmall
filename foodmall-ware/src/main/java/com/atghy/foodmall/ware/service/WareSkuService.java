package com.atghy.foodmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 收集库存量信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WareSkuEntity> getRawsByAgentname(String agentName);
}

