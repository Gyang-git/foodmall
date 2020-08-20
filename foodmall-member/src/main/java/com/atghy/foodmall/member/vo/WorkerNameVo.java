package com.atghy.foodmall.member.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-17
 * Description:
 */
@Data
public class WorkerNameVo {
    private List<ChefVo> chefs;

    private List<packorVo> packors;

    private List<TakemanVo> takemans;

    private List<VehicleVo> vehicles;
}
