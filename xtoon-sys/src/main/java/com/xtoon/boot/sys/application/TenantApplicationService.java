package com.xtoon.boot.sys.application;

import com.xtoon.boot.sys.domain.model.types.*;

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
     * @param mobile
     * @param password
     * @param userName
     */
    void registerTenant(TenantName tenantName, TenantCode tenantCode, Mobile mobile, Password password, UserName userName);

    /**
     * 禁用
     *
     * @param tenantId
     */
    void disable(TenantId tenantId);
}
