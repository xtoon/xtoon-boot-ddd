package com.xtoon.boot.domain.service;

import com.xtoon.boot.domain.model.Tenant;
import com.xtoon.boot.domain.model.user.User;

/**
 * 租户禁用Service
 *
 * @author haoxin
 * @date 2021-04-04
 **/
public class TenantDisableService {

    /**
     * 禁用
     *
     * @param tenant
     * @param creator
     */
    public void disable(Tenant tenant, User creator) {
        tenant.disable();
        creator.disable();
    }
}
