package com.xtoon.boot.interfaces.web;

import com.xtoon.boot.interfaces.common.Page;
import com.xtoon.boot.infrastructure.util.log.SysLog;
import com.xtoon.boot.infrastructure.util.validator.ValidatorUtils;
import com.xtoon.boot.interfaces.common.AbstractController;
import com.xtoon.boot.interfaces.common.CommonConstant;
import com.xtoon.boot.interfaces.common.Result;
import com.xtoon.boot.interfaces.facade.SysRoleServiceFacade;
import com.xtoon.boot.interfaces.facade.dto.RoleDTO;
import com.xtoon.boot.interfaces.web.command.RoleCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private SysRoleServiceFacade sysRoleServiceFacade;

    /**
     * 角色分页查询
     */
    @ApiOperation("角色分页查询")
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = sysRoleServiceFacade.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 角色列表
     */
    @ApiOperation("角色列表")
    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    public Result select(){
        List<RoleDTO> list = sysRoleServiceFacade.listAll();
        return Result.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @ApiOperation("角色信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:role:info")
    public Result info(@PathVariable("id") String id){
        RoleDTO role = sysRoleServiceFacade.getById(id);
        return Result.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @ApiOperation("保存角色")
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody RoleCommand roleCommand){
        ValidatorUtils.validateEntity(roleCommand);
        RoleDTO roleDTO = new RoleDTO(roleCommand.getId(),roleCommand.getRoleCode(),roleCommand.getRoleName(),
                roleCommand.getRemarks(), roleCommand.getPermissionIdList());
        sysRoleServiceFacade.saveOrUpdate(roleDTO);
        return Result.ok();
    }

    /**
     * 修改角色
     */
    @ApiOperation("修改角色")
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody RoleCommand roleCommand){
        ValidatorUtils.validateEntity(roleCommand);
        RoleDTO roleDTO = new RoleDTO(roleCommand.getId(),roleCommand.getRoleCode(),roleCommand.getRoleName(),
                roleCommand.getRemarks(), roleCommand.getPermissionIdList());
        sysRoleServiceFacade.saveOrUpdate(roleDTO);
        return Result.ok();
    }

    /**
     * 删除角色
     */
    @ApiOperation("删除角色")
    @SysLog("删除角色")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody String[] roleIds){
        sysRoleServiceFacade.deleteBatch(Arrays.asList(roleIds));
        return Result.ok();
    }

    /**
     * 禁用角色
     */
    @ApiOperation("禁用角色")
    @SysLog("禁用角色")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("sys:role:disable")
    public Result disable(@PathVariable("id") String id){
        sysRoleServiceFacade.disable(id);
        return Result.ok();
    }
}
