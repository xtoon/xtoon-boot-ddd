package com.xtoon.boot.sys.application;

import com.xtoon.boot.sys.application.command.AccountLoginCommand;
import com.xtoon.boot.sys.application.command.MobileLoginCommand;
import com.xtoon.boot.sys.application.command.RegisterTenantCommand;
import com.xtoon.boot.sys.application.dto.LoginSuccessDTO;

import java.awt.image.BufferedImage;

/**
 * 身份验证应用服务接口
 *
 * @author haoxin
 * @date 2021-05-10
 **/
public interface AuthenticationApplicationService {

    /**
     * 获取图片验证码
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 账号登录
     *
     * @param accountLoginCommand
     * @return
     */
    LoginSuccessDTO loginByAccount(AccountLoginCommand accountLoginCommand);

    /**
     * 手机号登录
     *
     * @param mobileLoginCommand
     * @return
     */
    LoginSuccessDTO loginByMobile(MobileLoginCommand mobileLoginCommand);

    /**
     * 登出
     *
     * @param userId
     */
    void logout(String userId);

    /**
     * 注册租户
     *
     * @param registerTenantCommand
     */
    void registerTenant(RegisterTenantCommand registerTenantCommand);

}
