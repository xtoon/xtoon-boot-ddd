package com.xtoon.boot.sys.domain.model.tenant;

import com.xtoon.boot.common.domain.StatusEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 租户测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("租户测试")
class TenantTest {

    static Tenant tenant;

    @BeforeAll
    static void setUp() {
        tenant = new Tenant(new TenantId("1"), new TenantCode("test"),new TenantName("测试租户"), StatusEnum.ENABLE,null);
    }

    @Test
    @DisplayName("是否有效")
    void isEnable() {
        assertTrue(tenant.isEnable());
    }

    @Test
    @DisplayName("禁用")
    void disable() {
        tenant.disable();
        assertFalse(tenant.isEnable());
    }

    @Test
    @DisplayName("是否相等")
    void sameIdentityAs() {
        Tenant tenant1 = new Tenant(new TenantId("1"), new TenantCode("test1"),new TenantName("测试租户1"), StatusEnum.ENABLE,null);
        assertTrue(tenant.sameIdentityAs(tenant1));
    }
}