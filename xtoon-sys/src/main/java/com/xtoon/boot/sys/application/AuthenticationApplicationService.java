package com.xtoon.boot.sys.application;

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
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);

    /**
     * 账号登录
     *
     * @param accountName
     * @param password
     * @return
     */
    LoginSuccessDTO loginByAccount(String accountName, String password);

    /**
     * 手机号登录
     *
     * @param mobile
     * @return
     */
    LoginSuccessDTO loginByMobile(String mobile);

    /**
     * 登出
     *
     * @param userId
     */
    void logout(String userId);

    /**
     * 注册租户
     *
     * @param tenantName
     * @param tenantCode
     * @param userName
     * @param mobile
     * @param password
     */
    void registerTenant(String tenantName, String tenantCode, String userName, String mobile, String password);

}
