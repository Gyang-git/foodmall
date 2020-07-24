package com.atghy.foodmall.food.service.impl;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.food.entity.SingleSetmealEntity;
import com.atghy.foodmall.food.service.SingleService;
import com.atghy.foodmall.food.service.SingleSetmealService;
import com.atghy.foodmall.food.vo.SetmealVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.SetmealDao;
import com.atghy.foodmall.food.entity.SetmealEntity;
import com.atghy.foodmall.food.service.SetmealService;
import org.springframework.transaction.annotation.Transactional;


@Service("setmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealDao, SetmealEntity> implements SetmealService {

    @Autowired
    SingleService singleService;

    @Autowired
    SingleSetmealService singleSetmealService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SetmealEntity> page = this.page(
                new Query<SetmealEntity>().getPage(params),
                new QueryWrapper<SetmealEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 非异步编排
     * @param setmealVo
     */
    @Override
    @Transactional
    public void addSetmeal(SetmealVo setmealVo) {
        //1-新增基础数据
        SetmealEntity setmealEntity = new SetmealEntity();
        BeanUtils.copyProperties(setmealVo,setmealEntity);
        System.out.println(setmealVo.getPrice());
        System.out.println(setmealEntity.getPrice());
        int insert = this.baseMapper.insert(setmealEntity);
        Long setmealId = setmealEntity.getId();
        //2-关联单品数据
        List<String> comSingleNames = setmealVo.getSetMealCom().getSingleName();
        List<String> comSingleQuants = setmealVo.getSetMealCom().getSingleQuantity();
        for (int i = 0 ; i < setmealVo.getComNum(); i++ ){
            if(comSingleNames.get(i) != null && comSingleQuants.get(i) != null && new Integer(comSingleQuants.get(i)) != 0){
                Long singleId = singleService.getSingleIdByName(comSingleNames.get(i));
                SingleSetmealEntity singleSetmealEntity = new SingleSetmealEntity();
                singleSetmealEntity.setSingleId(singleId);
                singleSetmealEntity.setSetmealId(setmealId);
                singleSetmealEntity.setStatus(0);
                singleSetmealService.save(singleSetmealEntity);
            }
        }
    }
}