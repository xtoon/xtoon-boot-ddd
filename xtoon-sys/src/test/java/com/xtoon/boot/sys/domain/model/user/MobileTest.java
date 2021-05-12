package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MobileTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@DisplayName("手机号测试")
class MobileTest {

    @Test
    @DisplayName("是否相等")
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Mobile("185555555551");
        });
        assertTrue(new Mobile("18555555555").sameValueAs(new Mobile("18555555555")));
        assertFalse(new Mobile("18555555555").sameValueAs(new Mobile("18555555554")));
    }
}