package com.atghy.foodmall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.MemberConstant;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.member.feign.TakeoutFeignService;
import com.atghy.foodmall.member.vo.*;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.WorkerDao;
import com.atghy.foodmall.member.entity.WorkerEntity;
import com.atghy.foodmall.member.service.WorkerService;


@Service("workerService")
public class WorkerServiceImpl extends ServiceImpl<WorkerDao, WorkerEntity> implements WorkerService {

    @Autowired
    TakeoutFeignService takeoutFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    WorkerService workerService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkerEntity> page = this.page(
                new Query<WorkerEntity>().getPage(params),
                new QueryWrapper<WorkerEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<WorkerEntity> getUnSignWorkerName() {
        LocalDateTime now = LocalDateTime.now();
        String end = LocalDateTime.of(LocalDate.from(now), LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String start = LocalDateTime.of(LocalDate.from(now), LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        QueryWrapper<WorkerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("save_time",start);
        List<WorkerEntity> list = workerService.list(queryWrapper);
        return list;
    }

    /**
     * 登记当天员工健康状况
     * @param
     * @return
     */
    @Override
    public Boolean signHealth(String name, String health, BigDecimal tem) throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        String end = LocalDateTime.of(LocalDate.from(now), LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String start = LocalDateTime.of(LocalDate.from(now), LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        QueryWrapper<WorkerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        WorkerEntity one = workerService.getOne(queryWrapper);
        Date save = one.getSaveTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endTime = dateFormat.parse(end);
        Date startTime = dateFormat.parse(start);
        if (save.before(endTime) && save.after(startTime)){
            //当日已经登记
            return false;
        }else{
            //当日未进行登记
            //id置空
            one.setHealth(health);
            one.setTem(tem);
            one.setSaveTime(new Date());
            workerService.updateById(one);
            //redis置空
            redisTemplate.delete(MemberConstant.REDIS_WORKER_SIGNED_SUCCESS_PREFIX);
            return true;
        }
    }

    @Override
    public WorkerNameVo getSignedWorkerName() {
        LocalDateTime now = LocalDateTime.now();
        String end = LocalDateTime.of(LocalDate.from(now), LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String start = LocalDateTime.of(LocalDate.from(now), LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        QueryWrapper<WorkerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("health","健康");
        queryWrapper.between("save_time",start,end);
        queryWrapper.between("tem","35.6","38.5");
        List<WorkerEntity> entities = baseMapper.selectList(queryWrapper);
        //将验证成功的防疫员工存于redis
        Object o = redisTemplate.opsForValue().get(MemberConstant.REDIS_WORKER_SIGNED_SUCCESS_PREFIX);
        if (o == null){
            //若redis为空则缓存
            String s = JSON.toJSONString(entities);
            redisTemplate.opsForValue().set(MemberConstant.REDIS_WORKER_SIGNED_SUCCESS_PREFIX,s,24, TimeUnit.HOURS);
        }
        List<ChefVo> chefVos = new ArrayList<>();
        List<packorVo> packorVos = new ArrayList<>();
        List<TakemanVo> takemanVos = new ArrayList<>();

        //员工配置
        for (WorkerEntity entity : entities) {
            if (entity.getWorkType().equals("厨师")){
                ChefVo chefVo = new ChefVo();
                chefVo.setName(entity.getName());
                chefVo.setId(entity.getId());
                chefVos.add(chefVo);
            }else if (entity.getWorkType().equals("包装员")){
                packorVo packorVo = new packorVo();
                packorVo.setId(entity.getId());
                packorVo.setName(entity.getName());
                packorVos.add(packorVo);
            }else if(entity.getWorkType().equals("配送员")){
                TakemanVo takemanVo = new TakemanVo();
                takemanVo.setId(entity.getId());
                takemanVo.setName(entity.getName());
                takemanVos.add(takemanVo);
            }
        }

        WorkerNameVo workerNameVo = new WorkerNameVo();
        //车辆配置
        R r = takeoutFeignService.getUnUseVehicle();
        if (r.getCode() == 0){
            List<VehicleVo> vehicles = r.getData("vehicles", new TypeReference<List<VehicleVo>>(){});
            workerNameVo.setVehicles(vehicles);
            workerNameVo.setChefs(chefVos);
            workerNameVo.setPackors(packorVos);
            workerNameVo.setTakemans(takemanVos);
            return workerNameVo;
        }
        return null;
    }
}