package com.atghy.foodmall.food.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-22
 * Description:
 */
@Data
public class SingleNatureForm {
    private Integer sour;
    private Integer salty;
    private Integer bitter;
    private Integer sweet;
    private Integer spicy;
    private Integer fry;
    private Integer taste;
    private Integer cool;
    private Integer fresh;
    private String categoryName;
    private Integer status;
}
