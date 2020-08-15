package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.member.entity.PackerEntity;
import com.atghy.foodmall.member.service.PackerService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集包装员个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:26:58
 */
@RestController
@RequestMapping("member/packer")
public class PackerController {
    @Autowired
    private PackerService packerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:packer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = packerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:packer:info")
    public R info(@PathVariable("id") Long id){
		PackerEntity packer = packerService.getById(id);

        return R.ok().put("packer", packer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:packer:save")
    public R save(@RequestBody PackerEntity packer){
		packerService.save(packer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:packer:update")
    public R update(@RequestBody PackerEntity packer){
		packerService.updateById(packer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:packer:delete")
    public R delete(@RequestBody Long[] ids){
		packerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
