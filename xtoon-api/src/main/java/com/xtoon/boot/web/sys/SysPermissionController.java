package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.log.SysLog;
import com.xtoon.boot.util.validator.ValidatorUtils;
import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import com.xtoon.boot.sys.facade.SysPermissionServiceFacade;
import com.xtoon.boot.sys.facade.dto.PermissionDTO;
import com.xtoon.boot.web.sys.command.PermissionCommand;
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
public class SysPermissionController extends AbstractController {

    @Autowired
    private SysPermissionServiceFacade sysPermissionServiceFacade;

    /**
     * 导航菜单
     */
    @ApiOperation("导航菜单")
    @GetMapping("/nav")
    public Result nav(){
        List<PermissionDTO> menuList = sysPermissionServiceFacade.getUserMenuTree(getUser());
        Set<String> permissions = getUser().getPermissionCodes();
        return Result.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有权限列表
     */
    @ApiOperation("所有权限列表")
    @GetMapping("/list")
    public Result list(){
        List<PermissionDTO> permissionList = sysPermissionServiceFacade.listAllPermission();
        return Result.ok().put("permissionList", permissionList);
    }

    /**
     * 选择菜单
     */
    @ApiOperation("选择菜单")
    @GetMapping("/selectMenu")
    public Result selectMenu(){
        List<PermissionDTO> menuList = sysPermissionServiceFacade.listAllMenu();
        return Result.ok().put("menuList", menuList);
    }

    /**
     * 权限信息
     */
    @ApiOperation("权限信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:permission:info")
    public Result info(@PathVariable("id") String id){
        PermissionDTO permission = sysPermissionServiceFacade.getById(id);
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
        PermissionDTO permissionDTO = new PermissionDTO(permissionCommand.getId(),permissionCommand.getParentId(),permissionCommand.getPermissionName(),
                permissionCommand.getPermissionType(),permissionCommand.getPermissionLevel(),permissionCommand.getPermissionCodes(),
                permissionCommand.getMenuIcon(),permissionCommand.getOrderNum(),permissionCommand.getMenuUrl());
        sysPermissionServiceFacade.saveOrUpdate(permissionDTO);
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
        PermissionDTO permissionDTO = new PermissionDTO(permissionCommand.getId(),permissionCommand.getParentId(),permissionCommand.getPermissionName(),
                permissionCommand.getPermissionType(),permissionCommand.getPermissionLevel(),permissionCommand.getPermissionCodes(),
                permissionCommand.getMenuIcon(),permissionCommand.getOrderNum(),permissionCommand.getMenuUrl());
        sysPermissionServiceFacade.saveOrUpdate(permissionDTO);
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
        sysPermissionServiceFacade.delete(id);
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
        sysPermissionServiceFacade.disable(id);
        return Result.ok();
    }

}
