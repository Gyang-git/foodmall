package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.food.vo.SingleVo;
import com.atghy.foodmall.food.vo.SkuHasStockVo;
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

    /**
     * 查询singles库存状态
     * @param singleIds
     * @return
     */
    @PostMapping("/hasStock")
    public R getSingleHasStock(@RequestBody List<Long> singleIds){
        List<SkuHasStockVo> vos = singleService.getSingleHasStock(singleIds);
        return R.ok().setData(vos);
    }

    //根据单品id上架
    @RequestMapping("/upSingle/{id}")
    public R upSingle(@PathVariable("id") Long id){
        Boolean isUp = singleService.upSingle(id);
        if(isUp){
            return R.ok();
        }else {
            return R.error(BizCodeEnume.FOOD_UP_EXCEPTION.getCode(),BizCodeEnume.FOOD_UP_EXCEPTION.getMsg());
        }
    }

    @GetMapping("getSingleByName/{name}")
    public R getSingleByName(@PathVariable("name")String name){
        SingleEntity entity = singleService.getSingleByName(name);
        return R.ok().put("single",entity);
    }

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
