package com.xtoon.boot.sys.domain.model.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * PasswordTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@Slf4j
class PasswordTest {

    @Test
    void create() {
        Password password = Password.create("123456");
        assertNotNull(password);
        log.info(password.toString());
    }

    @Test
    void create1() {
        Password password = Password.create("123456","abc");
        assertNotNull(password);
        log.info(password.toString());
    }

    @Test
    void sameValueAs() {
        Password password1 = Password.create("123456","abc");
        Password password2 = Password.create("123456","abc");
        assertTrue(password1.sameValueAs(password2));
    }
}