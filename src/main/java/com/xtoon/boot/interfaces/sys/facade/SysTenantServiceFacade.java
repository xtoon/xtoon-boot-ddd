package com.xtoon.boot.interfaces.sys.facade;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.shared.Page;

import java.util.Map;

/**
 * 租户Facade
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public interface SysTenantServiceFacade {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 注册租户
     *
     * @param tenantName
     * @param tenantCode
     * @param account
     */
    void registerTenant(String tenantName, String tenantCode, String userName, Account account);

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

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);
}
