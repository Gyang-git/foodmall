package com.atghy.foodmall.ware.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.ware.entity.AgentRawInfoEntity;
import com.atghy.foodmall.ware.service.AgentRawInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集代理商供应原料信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@RestController
@RequestMapping("ware/agentrawinfo")
public class AgentRawInfoController {
    @Autowired
    private AgentRawInfoService agentRawInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:agentrawinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = agentRawInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:agentrawinfo:info")
    public R info(@PathVariable("id") Long id){
		AgentRawInfoEntity agentRawInfo = agentRawInfoService.getById(id);

        return R.ok().put("agentRawInfo", agentRawInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:agentrawinfo:save")
    public R save(@RequestBody AgentRawInfoEntity agentRawInfo){
		agentRawInfoService.save(agentRawInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:agentrawinfo:update")
    public R update(@RequestBody AgentRawInfoEntity agentRawInfo){
		agentRawInfoService.updateById(agentRawInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:agentrawinfo:delete")
    public R delete(@RequestBody Long[] ids){
		agentRawInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
