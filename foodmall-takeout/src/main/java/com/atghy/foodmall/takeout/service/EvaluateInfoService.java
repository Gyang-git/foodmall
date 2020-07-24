package com.atghy.foodmall.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.takeout.entity.EvaluateInfoEntity;

import java.util.Map;

/**
 * 记录本次交易反馈
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
public interface EvaluateInfoService extends IService<EvaluateInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

