package com.xtoon.boot.sys.domain.model.role;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoleCodeTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class RoleCodeTest {

    @Test
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoleCode("你");
        });
        assertTrue(new RoleCode("5252com").sameValueAs(new RoleCode("5252com")));
        assertFalse(new RoleCode("5252com").sameValueAs(new RoleCode("5252co1")));
    }
}