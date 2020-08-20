package com.atghy.foodmall.takeout.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.MemberConstant;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.takeout.entity.VehicleEntity;
import com.atghy.foodmall.takeout.feign.OrderFeignService;
import com.atghy.foodmall.takeout.service.VehicleService;
import com.atghy.foodmall.takeout.vo.HealthMonitoringVo;
import com.atghy.foodmall.takeout.vo.OrderTakeoutVo;
import com.atghy.foodmall.takeout.vo.WorkerVo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.OutInfoDao;
import com.atghy.foodmall.takeout.entity.OutInfoEntity;
import com.atghy.foodmall.takeout.service.OutInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("outInfoService")
public class OutInfoServiceImpl extends ServiceImpl<OutInfoDao, OutInfoEntity> implements OutInfoService {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    OrderFeignService orderFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OutInfoEntity> page = this.page(
                new Query<OutInfoEntity>().getPage(params),
                new QueryWrapper<OutInfoEntity>()
        );

        return new PageUtils(page);
    }

    //TODO 异步编排
    @Override
    @Transactional
    public Boolean saveTakeout(OrderTakeoutVo orderTakeoutVo) {
        //再次确认车辆状态
        QueryWrapper<VehicleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_name",orderTakeoutVo.getVehicle());
        VehicleEntity entity = vehicleService.getOne(queryWrapper);
        if (entity.getUseStatus() == 0){
            //车辆空闲
            entity.setUseStatus(1);
            vehicleService.saveOrUpdate(entity);

            //保存配送信息
            String takeSn = IdWorker.getTimeId();
            OutInfoEntity outInfoEntity = new OutInfoEntity();
            outInfoEntity.setOrderSn(orderTakeoutVo.getOrderSn());
            outInfoEntity.setTakeSn(takeSn);
            outInfoEntity.setStartTime(new Date());
            outInfoEntity.setVehicleId(entity.getId());
            outInfoEntity.setTakemanName(orderTakeoutVo.getTakeoutName());
            outInfoEntity.setStatus(0);
            baseMapper.insert(outInfoEntity);

            //获取防疫员工信息 保存订单参与健康人员信息
            String s = redisTemplate.opsForValue().get(MemberConstant.REDIS_WORKER_SIGNED_SUCCESS_PREFIX);
            List<WorkerVo> workerVos = JSON.parseArray(s, WorkerVo.class);
            HealthMonitoringVo monitoringVo = new HealthMonitoringVo();
            monitoringVo.setStatus(0);
            for (WorkerVo vo : workerVos) {
                if (vo.getWorkType().equals("厨师") && vo.getName().equals(orderTakeoutVo.getChefName())){
                    monitoringVo.setChefId(vo.getId());
                    monitoringVo.setChefHeal(vo.getHealth());
                    monitoringVo.setChefTem(vo.getTem());
                }else if (vo.getWorkType().equals("包装员") && vo.getName().equals(orderTakeoutVo.getPackorName())){
                    monitoringVo.setPackerId(vo.getId());
                    monitoringVo.setPackerHeal(vo.getHealth());
                    monitoringVo.setPackerTem(vo.getTem());
                }else if (vo.getWorkType().equals("配送员") && vo.getName().equals(orderTakeoutVo.getTakeoutName())){
                    monitoringVo.setTakemanId(vo.getId());
                    monitoringVo.setTakemanHeal(vo.getHealth());
                    monitoringVo.setTakemanTem(vo.getTem());
                }
            }
            R r = orderFeignService.saveHealthMonitor(monitoringVo);
            if (r.getCode() == 0){
                //成功 更新订单信息
                Long id = r.getData("id", new TypeReference<Long>() {
                });
                R r1 = orderFeignService.takeoutOrder(orderTakeoutVo.getOrderSn(), takeSn, id);
                if (r1.getCode() == 0){
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean takeoutArrive(String orderSn) {
        //订单配送表
        QueryWrapper<OutInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn",orderSn);
        OutInfoEntity outInfoEntity = baseMapper.selectOne(queryWrapper);
        Long vehicleId = outInfoEntity.getVehicleId();
        String takeSn = outInfoEntity.getTakeSn();
        outInfoEntity.setArriveTime(new Date());
        int i = baseMapper.updateById(outInfoEntity);
        if (i == 1){
            //车辆归位
            VehicleEntity vehicleEntity = new VehicleEntity();
            vehicleEntity.setId(vehicleId);
            vehicleEntity.setUseStatus(0);
            vehicleService.updateById(vehicleEntity);
            //修改订单
            R r = orderFeignService.takeoutArrive(orderSn, takeSn);
            if (r.getCode() == 0){
                return true;
            }
        }
        return false;
    }
}