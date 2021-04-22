package com.xtoon.boot.sys.interfaces.facade;

import com.xtoon.boot.common.web.Page;

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
