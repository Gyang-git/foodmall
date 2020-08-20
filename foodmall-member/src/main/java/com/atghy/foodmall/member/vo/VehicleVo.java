package com.atghy.foodmall.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-17
 * Description:
 */
@Data
public class VehicleVo {
    /**
     *
     */
    private Long id;
    /**
     * 车辆名称
     */
    private String vehicleName;
    /**
     * 车辆类别
     */
    private String sort;
}
