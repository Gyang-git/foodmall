package com.atghy.foodmall.common.to.mq;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-08
 * Description:
 */
@Data
public class OrderLockTo {
    private Long id;
    /**
     *
     */
    private String orderSn;
    /**
     *
     */
    private Long skuId;
    /**
     *锁定状态
     */
    private Integer lockStatus;

    private String type;

    private String name;
    /**
     *
     */
    private Integer lockCount;
    /**
     *
     */

    private Date datetime;

}
