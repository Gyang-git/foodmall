package com.atghy.foodmall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.coupon.dao.SeckillFoodNoticeDao;
import com.atghy.foodmall.coupon.entity.SeckillFoodNoticeEntity;
import com.atghy.foodmall.coupon.service.SeckillFoodNoticeService;


@Service("seckillFoodNoticeService")
public class SeckillFoodNoticeServiceImpl extends ServiceImpl<SeckillFoodNoticeDao, SeckillFoodNoticeEntity> implements SeckillFoodNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillFoodNoticeEntity> page = this.page(
                new Query<SeckillFoodNoticeEntity>().getPage(params),
                new QueryWrapper<SeckillFoodNoticeEntity>()
        );

        return new PageUtils(page);
    }

}