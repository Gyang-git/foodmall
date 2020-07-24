package com.atghy.foodmall.takeout.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.AddressSecondDao;
import com.atghy.foodmall.takeout.entity.AddressSecondEntity;
import com.atghy.foodmall.takeout.service.AddressSecondService;


@Service("addressSecondService")
public class AddressSecondServiceImpl extends ServiceImpl<AddressSecondDao, AddressSecondEntity> implements AddressSecondService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AddressSecondEntity> page = this.page(
                new Query<AddressSecondEntity>().getPage(params),
                new QueryWrapper<AddressSecondEntity>()
        );

        return new PageUtils(page);
    }

}