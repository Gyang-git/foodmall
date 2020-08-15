package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.member.entity.TakemanEntity;
import com.atghy.foodmall.member.service.TakemanService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集派送员个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:26:58
 */
@RestController
@RequestMapping("member/takeman")
public class TakemanController {
    @Autowired
    private TakemanService takemanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:takeman:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = takemanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:takeman:info")
    public R info(@PathVariable("id") Long id){
		TakemanEntity takeman = takemanService.getById(id);

        return R.ok().put("takeman", takeman);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:takeman:save")
    public R save(@RequestBody TakemanEntity takeman){
		takemanService.save(takeman);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:takeman:update")
    public R update(@RequestBody TakemanEntity takeman){
		takemanService.updateById(takeman);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:takeman:delete")
    public R delete(@RequestBody Long[] ids){
		takemanService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
