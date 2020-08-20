package com.atghy.foodmall.coupon.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.coupon.dao.SeckillFoodRelationDao;
import com.atghy.foodmall.coupon.entity.SeckillFoodRelationEntity;
import com.atghy.foodmall.coupon.service.SeckillFoodRelationService;


@Service("seckillFoodRelationService")
public class SeckillFoodRelationServiceImpl extends ServiceImpl<SeckillFoodRelationDao, SeckillFoodRelationEntity> implements SeckillFoodRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<SeckillFoodRelationEntity> queryWrapper = new QueryWrapper<>();
        String promotionSessionId = (String) params.get("promotionSessionId");
        if (!StringUtils.isEmpty(promotionSessionId)){
            queryWrapper.eq("promotion_session_id",promotionSessionId);
        }
        IPage<SeckillFoodRelationEntity> page = this.page(
                new Query<SeckillFoodRelationEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}