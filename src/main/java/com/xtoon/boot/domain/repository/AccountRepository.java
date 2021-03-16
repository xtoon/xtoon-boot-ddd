package com.xtoon.boot.domain.repository;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.types.AccountId;
import com.xtoon.boot.domain.model.user.types.AccountName;
import com.xtoon.boot.domain.model.user.types.Mobile;

/**
 * 账号-Repository接口
 *
 * @author haoxin
 * @date 2021-02-21
 **/
public interface AccountRepository {

    /**
     * 根据手机号获取账号
     *
     * @param accountId
     * @return
     */
    Account find(AccountId accountId);

    /**
     * 根据手机号获取账号
     *
     * @param mobile
     * @return
     */
    Account find(Mobile mobile);

    /**
     * 根据账号获取账号
     *
     * @param accountName
     * @return
     */
    Account find(AccountName accountName);

    /**
     * 保存
     *
     * @param account
     */
    void store(Account account);
}
