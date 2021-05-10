package com.xtoon.boot.sys.domain.model.user;

import com.xtoon.boot.sys.domain.model.user.AccountId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * AccountIdTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class AccountIdTest {

    @Test
    void idString() {
        assertThat(new AccountId("1").getId()).isEqualTo("1");
    }

    @Test
    void sameValueAs() {
        assertThat(new AccountId("1").sameValueAs(new AccountId("2"))).isFalse();
        assertThat(new AccountId("1").sameValueAs(new AccountId("1"))).isTrue();
    }

}