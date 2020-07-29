package com.atghy.foodmall.food.controller;

import com.atghy.foodmall.food.service.SingleService;
import com.atghy.foodmall.food.vo.SingleItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-29
 * Description:
 */
@Controller
public class ItemController {
    @Autowired
    SingleService singleService;

    @GetMapping("singleItem/{singleId}.html")
    public String singleItem(@PathVariable("singleId")Long singleId, Model model){
        System.out.println("准备查询-" + singleId + "-单品");
        SingleItemVo vo = singleService.singleItem(singleId);
        model.addAttribute("singleItem",vo);
        return "item";
    }
}
