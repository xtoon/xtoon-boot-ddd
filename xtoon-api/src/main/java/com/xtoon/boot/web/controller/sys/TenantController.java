package com.xtoon.boot.web.controller.sys;

import com.xtoon.boot.web.common.AbstractController;
import com.xtoon.boot.web.common.Result;
import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.web.util.log.SysLog;
import com.xtoon.boot.sys.application.TenantApplicationService;
import com.xtoon.boot.sys.application.TenantQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 租户Controller
 *
 * @author haoxin
 * @date 2021-02-24
 **/
@Api(tags = "租户管理")
@RestController
@RequestMapping("/sys/tenant")
public class TenantController extends AbstractController {

    @Autowired
    private TenantQueryService tenantQueryService;

    @Autowired
    private TenantApplicationService tenantApplicationService;

    /**
     * 用户分页查询
     */
    @ApiOperation("租户分页查询")
    @GetMapping("/list")
    @RequiresPermissions("sys:tenant:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = tenantQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 禁用租户
     */
    @ApiOperation("禁用租户")
    @SysLog("禁用租户")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("sys:tenant:disable")
    public Result disable(@PathVariable("id") String id){
        tenantApplicationService.disable(id);
        return Result.ok();
    }
}
