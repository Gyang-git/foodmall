package com.atghy.foodmall.member.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.member.vo.WorkerNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.member.entity.WorkerEntity;
import com.atghy.foodmall.member.service.WorkerService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集厨师个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-16 14:39:12
 */
@RestController
@RequestMapping("member/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    /**
     * 每日体温登记
     */
    @GetMapping("/back/signHealth/{name}/{health}/{tem}")
    public R signHealth(@PathVariable("name") String name, @PathVariable("health") String health, @PathVariable("tem") BigDecimal tem) throws ParseException {
        Boolean b = workerService.signHealth(name,health,tem);
        if (b){
            return R.ok();
        }else{
            return R.error(BizCodeEnume.WORKER_HEALTH_SIGN_ERROR.getCode(),BizCodeEnume.WORKER_HEALTH_SIGN_ERROR.getMsg());
        }
    }

    @GetMapping("/back/getSignedWorkerName")
    public R getSignedWorkerName(){
        WorkerNameVo entities = workerService.getSignedWorkerName();
        return R.ok().put("entities",entities);
    }

    /**
     * 获取最近二十四小时尚未进行体温登记的姓名
     * @return
     */
    @GetMapping("/back/getUnSignWorkerName")
    public R getUnSignWorkerName(){
        List<WorkerEntity> entities = workerService.getUnSignWorkerName();
        return R.ok().put("entities",entities);
    }

    /**
     * 后台获取信息
     * @return
     */
    @GetMapping("/back/getWorkerInfo")
    public R getWorkerInfo(){
        List<WorkerEntity> list = workerService.list();
        return R.ok().put("list",list);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:worker:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:worker:info")
    public R info(@PathVariable("id") Long id){
		WorkerEntity worker = workerService.getById(id);

        return R.ok().put("worker", worker);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:worker:save")
    public R save(@RequestBody WorkerEntity worker){
		workerService.save(worker);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:worker:update")
    public R update(@RequestBody WorkerEntity worker){
		workerService.updateById(worker);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:worker:delete")
    public R delete(@RequestBody Long[] ids){
		workerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
