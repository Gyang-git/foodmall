package com.atghy.foodmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.WorkerNameEntity;

import java.util.Map;

/**
 * 
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-16 21:29:12
 */
public interface WorkerNameService extends IService<WorkerNameEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

