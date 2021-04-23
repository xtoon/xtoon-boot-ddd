package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.common.util.log.SysLog;
import com.xtoon.boot.sys.facade.UserFacadeService;
import com.xtoon.boot.sys.facade.dto.UserDTO;
import com.xtoon.boot.util.validator.ValidatorUtils;
import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import com.xtoon.boot.web.sys.command.PasswordCommand;
import com.xtoon.boot.web.sys.command.UserCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author haoxin
 * @date 2021-02-20
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private UserFacadeService userFacadeService;

    /**
     * 用户分页查询
     */
    @ApiOperation("用户分页查询")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = userFacadeService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 获取登录的用户信息
     */
    @ApiOperation("获取登录的用户信息")
    @GetMapping("/info")
    public Result info(){
        return Result.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation("修改密码")
    @SysLog("修改密码")
    @PostMapping("/password")
    public Result changePassword(@RequestBody PasswordCommand passwordCommand){
        ValidatorUtils.validateEntity(passwordCommand);
        userFacadeService.changePassword(getUser().getId(),passwordCommand.getPassword(),passwordCommand.getNewPassword());
        return Result.ok();
    }

    /**
     * 用户信息
     */
    @ApiOperation("用户信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:user:info")
    public Result info(@PathVariable("id") String id){
        return Result.ok().put("user", userFacadeService.find(id));
    }

    /**
     * 保存用户
     */
    @ApiOperation("保存用户")
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody UserCommand userCommand){
        ValidatorUtils.validateEntity(userCommand, AddGroup.class);
        userFacadeService.save(new UserDTO(userCommand.getId(),userCommand.getUserName(),userCommand.getEmail(),userCommand.getMobile(),
                null,userCommand.getRoleIdList()));
        return Result.ok();
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody UserCommand userCommand){
        ValidatorUtils.validateEntity(userCommand, UpdateGroup.class);
        userFacadeService.update(new UserDTO(userCommand.getId(),userCommand.getUserName(),userCommand.getEmail(),userCommand.getMobile(),
                null,userCommand.getRoleIdList()));
        return Result.ok();
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody String[] userIds){
        userFacadeService.deleteBatch(Arrays.asList(userIds));
        return Result.ok();
    }

    /**
     * 禁用用户
     */
    @ApiOperation("禁用用户")
    @SysLog("禁用用户")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("sys:user:disable")
    public Result disable(@PathVariable("id") String id){
        userFacadeService.disable(id);
        return Result.ok();
    }
}
