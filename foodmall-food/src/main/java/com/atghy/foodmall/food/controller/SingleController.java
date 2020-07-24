package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;


import com.atghy.foodmall.food.vo.SingleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.food.entity.SingleEntity;
import com.atghy.foodmall.food.service.SingleService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集所有单菜品表
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 14:14:27
 */
@RestController
@RequestMapping("food/single")
public class SingleController {
    @Autowired
    private SingleService singleService;

    //根据name查询id
    @GetMapping("/getSingleIdByName/{name}")
    public Long getSingleIdByName(@PathVariable("name") String name){
        Long id = singleService.getSingleIdByName(name);
        return id;
    }

    //添加单品信息
    @RequestMapping("/addSingle")
    public R addSingle(@RequestBody SingleVo singleVo) throws ExecutionException, InterruptedException {
        singleService.addSingle(singleVo);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:single:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = singleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:single:info")
    public R info(@PathVariable("id") Long id){
		SingleEntity single = singleService.getById(id);

        return R.ok().put("single", single);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:single:save")
    public R save(@RequestBody SingleEntity single){
		singleService.save(single);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:single:update")
    public R update(@RequestBody SingleEntity single){
		singleService.updateById(single);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:single:delete")
    public R delete(@RequestBody Long[] ids){
		singleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
