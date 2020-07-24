package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.member.entity.AgentEntity;
import com.atghy.foodmall.member.service.AgentService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集供应商信息 外联供应商原料信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@RestController
@RequestMapping("member/agent")
public class AgentController {
    @Autowired
    private AgentService agentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:agent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = agentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:agent:info")
    public R info(@PathVariable("id") Long id){
		AgentEntity agent = agentService.getById(id);

        return R.ok().put("agent", agent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:agent:save")
    public R save(@RequestBody AgentEntity agent){
		agentService.save(agent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:agent:update")
    public R update(@RequestBody AgentEntity agent){
		agentService.updateById(agent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:agent:delete")
    public R delete(@RequestBody Long[] ids){
		agentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //删除单个
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody Long id){
        agentService.removeById(id);
        return R.ok();
    }

    @GetMapping("/getAgentIdByName/{name}")
    public Long getAgentIdByName(@PathVariable("name") String name){
        Long agengName = agentService.getAgentIdByName(name);
        return agengName;
    }
}
