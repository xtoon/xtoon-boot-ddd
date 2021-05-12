package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EmailTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@DisplayName("email测试")
class EmailTest {

    @Test
    @DisplayName("是否相等")
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Email("1");
        });
        assertTrue(new Email("5252@qq.com").sameValueAs(new Email("5252@qq.com")));
        assertFalse(new Email("5252@qq.com").sameValueAs(new Email("52523@qq.com")));
    }
}