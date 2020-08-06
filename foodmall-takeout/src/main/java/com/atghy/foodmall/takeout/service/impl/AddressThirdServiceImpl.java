package com.atghy.foodmall.takeout.service.impl;

import com.atghy.foodmall.takeout.vo.AddressVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.AddressThirdDao;
import com.atghy.foodmall.takeout.entity.AddressThirdEntity;
import com.atghy.foodmall.takeout.service.AddressThirdService;


@Service("addressThirdService")
public class AddressThirdServiceImpl extends ServiceImpl<AddressThirdDao, AddressThirdEntity> implements AddressThirdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AddressThirdEntity> page = this.page(
                new Query<AddressThirdEntity>().getPage(params),
                new QueryWrapper<AddressThirdEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<AddressVo> getAddress() {
        AddressThirdDao addressThirdDao = this.baseMapper;
        List<AddressVo> addressVos = addressThirdDao.getAddress();
        return addressVos;
    }

}