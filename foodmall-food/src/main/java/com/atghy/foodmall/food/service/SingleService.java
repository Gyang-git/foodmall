package com.atghy.foodmall.food.service;

import com.atghy.foodmall.food.vo.SingleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.SingleEntity;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 收集所有单菜品表
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 14:14:27
 */
public interface SingleService extends IService<SingleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addSingle(SingleVo singleVo) throws ExecutionException, InterruptedException;

    Long getSingleIdByName(String name);
}

