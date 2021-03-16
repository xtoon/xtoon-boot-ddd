package com.xtoon.boot.interfaces.sys.web;

import com.xtoon.boot.infrastructure.common.Result;
import com.xtoon.boot.infrastructure.util.log.SysLog;
import com.xtoon.boot.infrastructure.util.redis.RedisUtils;
import com.xtoon.boot.infrastructure.util.validator.ValidatorUtils;
import com.xtoon.boot.infrastructure.util.validator.group.AddGroup;
import com.xtoon.boot.interfaces.common.AbstractController;
import com.xtoon.boot.interfaces.common.InterfacesConstant;
import com.xtoon.boot.interfaces.sys.facade.SysCaptchaServiceFacade;
import com.xtoon.boot.interfaces.sys.facade.SysTenantServiceFacade;
import com.xtoon.boot.interfaces.sys.facade.SysUserServiceFacade;
import com.xtoon.boot.interfaces.sys.web.command.RegisterTenantCommand;
import com.xtoon.boot.interfaces.sys.web.command.RegisterUserCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册Controller
 *
 * @author haoxin
 * @date 2021-02-13
 **/
@Api(tags = "注册")
@RestController
public class SysRegisterController extends AbstractController {

    @Autowired
    private SysTenantServiceFacade sysTenantServiceFacade;

    @Autowired
    private SysCaptchaServiceFacade sysCaptchaServiceFacade;

    @Autowired
    private SysUserServiceFacade sysUserServiceFacade;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 注册租户
     */
    @ApiOperation("注册租户")
    @SysLog("注册租户")
    @PostMapping("/sys/registerTenant")
    public Result registerTenantAndUser(@RequestBody RegisterTenantCommand registerTenantCommand) {
        ValidatorUtils.validateEntity(registerTenantCommand, AddGroup.class);
        boolean captcha = sysCaptchaServiceFacade.validate(registerTenantCommand.getUuid(), registerTenantCommand.getCaptcha());
        if(!captcha){
            return Result.error("验证码不正确");
        }
        sysTenantServiceFacade.registerTenant(registerTenantCommand.getTenantName(),registerTenantCommand.getTenantCode(),registerTenantCommand.getUserName(),
                registerTenantCommand.getMobile(),registerTenantCommand.getPassword());
        return Result.ok();
    }


    /**
     * 注册用户
     */
    @ApiOperation("注册用户")
    @SysLog("注册用户")
    @PostMapping("/sys/registerUser")
    public Result registerUser(@RequestBody RegisterUserCommand registerUserCommand) {
        ValidatorUtils.validateEntity(registerUserCommand);
        String verificationCodeRedis = redisUtils.get(InterfacesConstant.REDIS_PHONE_CODE + registerUserCommand.getMobile());
        if (!registerUserCommand.getVerificationCode().equals(verificationCodeRedis)) {
            return Result.error("验证码不正确");
        }
        sysUserServiceFacade.registerUser(registerUserCommand.getTenantId(),registerUserCommand.getRoleId(),registerUserCommand.getMobile(),registerUserCommand.getUserName());
        return Result.ok();
    }
}
