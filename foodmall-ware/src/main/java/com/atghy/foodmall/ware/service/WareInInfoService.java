package com.atghy.foodmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.ware.entity.WareInInfoEntity;

import java.util.Map;

/**
 * 记录向供应商采购的信息表
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
public interface WareInInfoService extends IService<WareInInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

