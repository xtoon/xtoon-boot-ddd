package com.xtoon.boot.sys.domain.model.types;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        Token token = Token.create("11");
        assertThat(token).isNotNull();
        log.info(token.toString());
    }
}