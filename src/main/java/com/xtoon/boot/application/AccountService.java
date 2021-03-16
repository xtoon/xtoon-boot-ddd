package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.types.AccountId;
import com.xtoon.boot.domain.model.user.types.AccountName;
import com.xtoon.boot.domain.model.user.types.Mobile;

/**
 * 用户Service
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public interface AccountService {

    /**
     * 通过账号登录
     *
     * @param accountName
     * @param password
     * @return
     */
    Account login(AccountName accountName, String password);

    /**
     * 通过手机号登录
     *
     * @param mobile
     * @return
     */
    Account login(Mobile mobile);

    /**
     * 登出
     *
     * @param accountId
     */
    void logout(AccountId accountId);

    /**
     * 修改密码
     *
     * @param account
     * @param oldPasswordStr
     * @param newPasswordStr
     */
    void changePassword(Account account, String oldPasswordStr, String newPasswordStr);
}
