package com.xtoon.boot.web.controller.sys;

import com.xtoon.boot.web.common.AbstractController;
import com.xtoon.boot.web.common.Result;
import com.xtoon.boot.common.util.validator.ValidatorUtils;
import com.xtoon.boot.common.util.validator.group.AddGroup;
import com.xtoon.boot.common.util.validator.group.UpdateGroup;
import com.xtoon.boot.sys.application.PermissionApplicationService;
import com.xtoon.boot.sys.application.PermissionQueryService;
import com.xtoon.boot.sys.application.command.PermissionCommand;
import com.xtoon.boot.sys.application.dto.PermissionDTO;
import com.xtoon.boot.web.util.log.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 权限Controller
 *
 * @author haoxin
 * @date 2021-02-16
 **/
@Api(tags = "权限管理")
@RestController
@RequestMapping("/sys/permission")
public class PermissionController extends AbstractController {

    @Autowired
    private PermissionApplicationService permissionApplicationService;

    @Autowired
    private PermissionQueryService permissionQueryService;

    /**
     * 导航菜单
     */
    @ApiOperation("导航菜单")
    @GetMapping("/nav")
    public Result nav(){
        List<PermissionDTO> menuList = permissionQueryService.getUserMenuTree(getUser().getPermissionIds());
        Set<String> permissions = getUser().getPermissionCodes();
        return Result.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有权限列表
     */
    @ApiOperation("所有权限列表")
    @GetMapping("/list")
    public Result list(){
        List<PermissionDTO> permissionList = permissionQueryService.listAllPermission();
        return Result.ok().put("permissionList", permissionList);
    }

    /**
     * 选择菜单
     */
    @ApiOperation("选择菜单")
    @GetMapping("/selectMenu")
    public Result selectMenu(){
        List<PermissionDTO> menuList = permissionQueryService.listAllMenu();
        return Result.ok().put("menuList", menuList);
    }

    /**
     * 权限信息
     */
    @ApiOperation("权限信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:permission:info")
    public Result info(@PathVariable("id") String id){
        PermissionDTO permission = permissionQueryService.getById(id);
        return Result.ok().put("permission", permission);
    }

    /**
     * 保存权限
     */
    @ApiOperation("保存权限")
    @SysLog("保存权限")
    @PostMapping("/save")
    @RequiresPermissions("sys:permission:save")
    public Result save(@RequestBody PermissionCommand permissionCommand){
        ValidatorUtils.validateEntity(permissionCommand, AddGroup.class);
        permissionApplicationService.saveOrUpdate(permissionCommand);
        return Result.ok();
    }

    /**
     * 修改权限
     */
    @ApiOperation("修改权限")
    @SysLog("修改权限")
    @PostMapping("/update")
    @RequiresPermissions("sys:permission:update")
    public Result update(@RequestBody PermissionCommand permissionCommand){
        ValidatorUtils.validateEntity(permissionCommand, UpdateGroup.class);
        permissionApplicationService.saveOrUpdate(permissionCommand);
        return Result.ok();
    }

    /**
     * 删除权限
     */
    @ApiOperation("删除权限")
    @SysLog("删除权限")
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:permission:delete")
    public Result delete(@PathVariable("id") String id){
        permissionApplicationService.delete(id);
        return Result.ok();
    }

    /**
     * 禁用权限
     */
    @ApiOperation("禁用权限")
    @SysLog("禁用权限")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("sys:permission:disable")
    public Result disable(@PathVariable("id") String id){
        permissionApplicationService.disable(id);
        return Result.ok();
    }

}
