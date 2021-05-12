package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AccountIdTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class AccountIdTest {

    @Test
    void idString() {
        assertEquals(new AccountId("1").getId(),"1");
    }

    @Test
    void sameValueAs() {
        assertFalse(new AccountId("1").sameValueAs(new AccountId("2")));
        assertTrue(new AccountId("1").sameValueAs(new AccountId("1")));
    }

}