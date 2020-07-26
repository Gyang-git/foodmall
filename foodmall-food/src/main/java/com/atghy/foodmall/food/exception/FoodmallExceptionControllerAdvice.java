package com.atghy.foodmall.food.exception;

import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-25
 * Description:
 */
@Slf4j
@RestControllerAdvice("com.atghy.foodmall.food.controller")
public class FoodmallExceptionControllerAdvice {
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("错误：",throwable);
        return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(),BizCodeEnume.VAILD_EXCEPTION.getMsg());
    }
}
