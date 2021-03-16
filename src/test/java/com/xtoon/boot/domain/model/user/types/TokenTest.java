package com.xtoon.boot.domain.model.user.types;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TokenTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@Slf4j
class TokenTest {

    @Test
    void create() {
        Token token = Token.create();
        assertThat(token).isNotNull();
        log.info(token.toString());
    }
}