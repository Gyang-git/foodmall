package com.atghy.foodmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.ware.entity.WareOutInfoEntity;

import java.util.Map;

/**
 * 收集仓库原料出货明细
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
public interface WareOutInfoService extends IService<WareOutInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

