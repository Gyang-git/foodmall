package com.atghy.foodmall.takeout.service;

import com.atghy.foodmall.takeout.vo.OrderTakeoutVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.takeout.entity.OutInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 记录外卖派送信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
public interface OutInfoService extends IService<OutInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean saveTakeout(OrderTakeoutVo orderTakeoutVo);

    Boolean takeoutArrive(String orderSn);

}

