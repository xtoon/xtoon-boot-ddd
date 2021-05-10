package com.xtoon.boot.sys.domain.model.user;

import com.xtoon.boot.sys.domain.model.user.Password;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(password).isNotNull();
        log.info(password.toString());
    }

    @Test
    void create1() {
        Password password = Password.create("123456","abc");
        assertThat(password).isNotNull();
        log.info(password.toString());
    }

    @Test
    void sameValueAs() {
        Password password1 = Password.create("123456","abc");
        Password password2 = Password.create("123456","abc");
        assertThat(password1.sameValueAs(password2)).isTrue();
    }
}