package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 账号名称测试
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@DisplayName("账号名称测试")
class AccountNameTest {

    @Test
    @DisplayName("账号相等")
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountName("");
        });
        assertFalse(new AccountName("test").sameValueAs(new AccountName("test1")));
        assertTrue(new AccountName("test").sameValueAs(new AccountName("test")));
    }
}