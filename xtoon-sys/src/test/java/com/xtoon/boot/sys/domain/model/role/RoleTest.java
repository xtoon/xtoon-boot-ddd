package com.xtoon.boot.sys.domain.model.role;

import com.xtoon.boot.common.domain.StatusEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 角色测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("角色测试")
class RoleTest {

    static Role role;

    @BeforeAll
    static void setUp() {
        role = new Role(new RoleId("1"),new RoleCode("test"),new RoleName("测试"),"", StatusEnum.ENABLE,null);
    }

    @Test
    @DisplayName("角色禁用")
    void disable() {
        role.disable();
        assertEquals(role.getStatus(), StatusEnum.DISABLE);
    }
}