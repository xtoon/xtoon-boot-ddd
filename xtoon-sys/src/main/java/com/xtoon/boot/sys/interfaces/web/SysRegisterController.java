package com.xtoon.boot.sys.interfaces.web;

import com.xtoon.boot.common.web.AbstractController;
import com.xtoon.boot.common.web.Result;
import com.xtoon.boot.common.util.log.SysLog;
import com.xtoon.boot.common.util.validator.ValidatorUtils;
import com.xtoon.boot.common.util.validator.group.AddGroup;
import com.xtoon.boot.sys.interfaces.facade.SysCaptchaServiceFacade;
import com.xtoon.boot.sys.interfaces.facade.SysTenantServiceFacade;
import com.xtoon.boot.sys.interfaces.web.command.RegisterTenantCommand;
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
}
