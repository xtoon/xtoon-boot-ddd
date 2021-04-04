package com.xtoon.boot.domain.model.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 类描述
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class AccountNameTest {

    @Test
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountName("");
        });
        assertThat(new AccountName("test").sameValueAs(new AccountName("test1"))).isFalse();
        assertThat(new AccountName("test").sameValueAs(new AccountName("test"))).isTrue();
    }
}