package com.xtoon.boot.sys.domain.model.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoleCodeTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@DisplayName("角色编码测试")
class RoleCodeTest {

    @Test
    @DisplayName("角色编码相等")
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoleCode("你");
        });
        assertTrue(new RoleCode("5252com").sameValueAs(new RoleCode("5252com")));
        assertFalse(new RoleCode("5252com").sameValueAs(new RoleCode("5252co1")));
    }
}