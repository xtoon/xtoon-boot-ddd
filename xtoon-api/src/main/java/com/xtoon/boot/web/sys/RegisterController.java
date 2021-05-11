package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.validator.ValidatorUtils;
import com.xtoon.boot.common.util.validator.group.AddGroup;
import com.xtoon.boot.sys.application.AuthenticationApplicationService;
import com.xtoon.boot.sys.application.command.RegisterTenantCommand;
import com.xtoon.boot.util.log.SysLog;
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
public class RegisterController extends AbstractController {

    @Autowired
    private AuthenticationApplicationService authenticationApplicationService;

    /**
     * 注册租户
     */
    @ApiOperation("注册租户")
    @SysLog("注册租户")
    @PostMapping("/sys/registerTenant")
    public Result registerTenantAndUser(@RequestBody RegisterTenantCommand registerTenantCommand) {
        ValidatorUtils.validateEntity(registerTenantCommand, AddGroup.class);
        authenticationApplicationService.registerTenant(registerTenantCommand);
        return Result.ok();
    }
}
