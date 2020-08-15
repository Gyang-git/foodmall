package com.atghy.foodmall.food.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-08
 * Description: 对库存数据进行锁定 解锁
 */
@Mapper
public interface FoodStockDao {
    static void unLockStock(@Param("dbName") String dbName, @Param("skuId") Long skuId, @Param("lockCount") Integer lockCount) {
    }
}
