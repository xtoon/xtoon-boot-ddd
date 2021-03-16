package com.xtoon.boot.domain.model.user.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MobileTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class MobileTest {

    @Test
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Mobile("185555555551");
        });
        assertThat(new Mobile("18555555555").sameValueAs(new Mobile("18555555555"))).isTrue();
        assertThat(new Mobile("18555555555").sameValueAs(new Mobile("18555555554"))).isFalse();
    }
}