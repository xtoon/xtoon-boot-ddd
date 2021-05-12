package com.xtoon.boot.sys.domain.model.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertNotNull(token);
        log.info(token.toString());
    }
}