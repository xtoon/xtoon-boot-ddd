package com.xtoon.boot.sys.domain.model.user;

import com.xtoon.boot.sys.domain.model.user.Email;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * EmailTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class EmailTest {

    @Test
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Email("1");
        });
        assertThat(new Email("5252@qq.com").sameValueAs(new Email("5252@qq.com"))).isTrue();
        assertThat(new Email("5252@qq.com").sameValueAs(new Email("52523@qq.com"))).isFalse();
    }
}