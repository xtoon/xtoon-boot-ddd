package com.xtoon.boot.sys.domain.model.tenant;

import com.xtoon.boot.sys.domain.model.tenant.Tenant;
import com.xtoon.boot.sys.domain.model.tenant.TenantCode;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import com.xtoon.boot.sys.domain.model.tenant.TenantName;

/**
 * 租户-Repository接口
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public interface TenantRepository {

    /**
     * 通过租户id获取租户
     *
     * @param tenantId
     * @return
     */
    Tenant find(TenantId tenantId);

    /**
     * 通过租户名称获取租户
     *
     * @param tenantName
     * @return
     */
    Tenant find(TenantName tenantName);

    /**
     * 通过租户编码获取租户
     *
     * @param tenantCode
     * @return
     */
    Tenant find(TenantCode tenantCode);

    /**
     * 保存
     *
     * @param tenant
     */
    TenantId store(Tenant tenant);

}
