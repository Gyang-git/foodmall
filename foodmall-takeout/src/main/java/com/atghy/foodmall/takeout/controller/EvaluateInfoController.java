package com.atghy.foodmall.takeout.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.takeout.entity.EvaluateInfoEntity;
import com.atghy.foodmall.takeout.service.EvaluateInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 记录本次交易反馈
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@RestController
@RequestMapping("takeout/evaluateinfo")
public class EvaluateInfoController {
    @Autowired
    private EvaluateInfoService evaluateInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("takeout:evaluateinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = evaluateInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("takeout:evaluateinfo:info")
    public R info(@PathVariable("id") Long id){
		EvaluateInfoEntity evaluateInfo = evaluateInfoService.getById(id);

        return R.ok().put("evaluateInfo", evaluateInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("takeout:evaluateinfo:save")
    public R save(@RequestBody EvaluateInfoEntity evaluateInfo){
		evaluateInfoService.save(evaluateInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("takeout:evaluateinfo:update")
    public R update(@RequestBody EvaluateInfoEntity evaluateInfo){
		evaluateInfoService.updateById(evaluateInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("takeout:evaluateinfo:delete")
    public R delete(@RequestBody Long[] ids){
		evaluateInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
