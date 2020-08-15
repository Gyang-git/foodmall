package com.atghy.foodmall.coupon.service.impl;

import com.atghy.foodmall.coupon.entity.SeckillFoodRelationEntity;
import com.atghy.foodmall.coupon.service.SeckillFoodRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.coupon.dao.SeckillSessionDao;
import com.atghy.foodmall.coupon.entity.SeckillSessionEntity;
import com.atghy.foodmall.coupon.service.SeckillSessionService;

import javax.management.QueryEval;


@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

    @Autowired
    SeckillFoodRelationService seckillFoodRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSessionEntity> page = this.page(
                new Query<SeckillSessionEntity>().getPage(params),
                new QueryWrapper<SeckillSessionEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取最近三天的秒杀场次
     * @return
     */
    @Override
    public List<SeckillSessionEntity> getLates3DaySession() {
        QueryWrapper<SeckillSessionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("start_time",startTime(),endTime());
        List<SeckillSessionEntity> list = list(queryWrapper);
        if (list != null && list.size() > 0){
            List<SeckillSessionEntity> collect = list.stream().map(session -> {
                //当前活动场次id
                Long id = session.getId();
                QueryWrapper<SeckillFoodRelationEntity> wrapper = new QueryWrapper<>();
                wrapper.eq("promotion_session_id", id);
                List<SeckillFoodRelationEntity> foodRelationEntities = seckillFoodRelationService.list(wrapper);
                session.setRelationSkus(foodRelationEntities);
                return session;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

    private String endTime(){
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(2);
        LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
        String format = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return  format;
    }

    private String startTime() {
        LocalDate now = LocalDate.now();
        LocalTime min = LocalTime.MIN;
        LocalDateTime startTime = LocalDateTime.of(now, min);
        String format = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return  format;
    }
}