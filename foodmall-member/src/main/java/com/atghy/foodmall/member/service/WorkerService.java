package com.atghy.foodmall.member.service;

import com.atghy.foodmall.member.vo.WorkerNameVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.member.entity.WorkerEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 收集厨师个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-16 14:39:12
 */
public interface WorkerService extends IService<WorkerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WorkerEntity> getUnSignWorkerName();

    Boolean signHealth(String name, String health, BigDecimal tem) throws ParseException;

    WorkerNameVo getSignedWorkerName();

}

