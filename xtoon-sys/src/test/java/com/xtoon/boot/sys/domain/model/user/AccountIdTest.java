package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 账号ID测试
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@DisplayName("账号ID测试")
class AccountIdTest {

    @Test
    @DisplayName("获取ID")
    void idString() {
        assertEquals(new AccountId("1").getId(),"1");
    }

    @Test
    @DisplayName("相等")
    void sameValueAs() {
        assertFalse(new AccountId("1").sameValueAs(new AccountId("2")));
        assertTrue(new AccountId("1").sameValueAs(new AccountId("1")));
    }

}