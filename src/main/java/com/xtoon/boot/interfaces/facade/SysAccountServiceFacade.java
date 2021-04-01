package com.xtoon.boot.interfaces.facade;

import com.xtoon.boot.interfaces.facade.dto.LoginSuccessDTO;

/**
 * 账号Facade
 *
 * @author haoxin
 * @date 2021-02-08
 **/
public interface SysAccountServiceFacade {

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

}
