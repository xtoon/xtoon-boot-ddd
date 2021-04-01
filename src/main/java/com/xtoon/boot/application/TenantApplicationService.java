package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.UserName;

/**
 * 租户Service
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public interface TenantApplicationService {

    /**
     * 注册租户
     *
     * @param tenantName
     * @param tenantCode
     * @param userName
     * @param account
     */
    void registerTenant(TenantName tenantName, TenantCode tenantCode, UserName userName, Account account);

    /**
     * 禁用
     *
     * @param tenantId
     */
    void disable(TenantId tenantId);
}
