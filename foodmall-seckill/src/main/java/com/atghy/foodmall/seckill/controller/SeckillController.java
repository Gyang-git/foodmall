package com.atghy.foodmall.seckill.controller;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.seckill.service.SeckillService;
import com.atghy.foodmall.seckill.to.SeckillFoodRedisTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-14
 * Description:
 */
@Controller
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    /**
     * 获取当前时间可参与的秒杀
     * @return
     */
    @ResponseBody
    @GetMapping("/currentSeckillFoods")
    public R getCurrentSeckillFoods(){
        List<SeckillFoodRedisTo> vos =  seckillService.getCurrentSeckillFoods();
        return R.ok().setData(vos);
    }

    @ResponseBody
    @GetMapping("/food/seckill/{foodId}")
    public R getFoodSeckillInfo(@PathVariable("foodId") Long foodId){
        SeckillFoodRedisTo to = seckillService.getFoodSeckillInfo(foodId);
        return R.ok().setData(to);
    }

    @GetMapping("/kill")
    public String seckill(@RequestParam("killId") String killId,
                          @RequestParam("key") String key,
                          @RequestParam("num") Integer num,
                          Model model){
        String orderSn = seckillService.kill(killId,key,num);
        model.addAttribute("orderSn",orderSn);
        return "success";
    }

}
