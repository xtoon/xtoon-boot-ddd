package com.xtoon.boot.interfaces.sys.web;

import com.xtoon.boot.domain.shared.Page;
import com.xtoon.boot.infrastructure.common.Result;
import com.xtoon.boot.infrastructure.util.log.SysLog;
import com.xtoon.boot.interfaces.common.AbstractController;
import com.xtoon.boot.interfaces.sys.facade.SysTenantServiceFacade;
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
public class SysTenantController extends AbstractController {

    @Autowired
    private SysTenantServiceFacade sysTenantServiceFacade;

    /**
     * 用户分页查询
     */
    @ApiOperation("租户分页查询")
    @GetMapping("/list")
    @RequiresPermissions("sys:tenant:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = sysTenantServiceFacade.queryPage(params);
        return Result.ok().put(RESULT_PAGE, page);
    }

    /**
     * 禁用租户
     */
    @ApiOperation("禁用租户")
    @SysLog("禁用租户")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("sys:tenant:disable")
    public Result disable(@PathVariable("id") String id){
        sysTenantServiceFacade.disable(id);
        return Result.ok();
    }
}
