package com.xtoon.boot.sys.domain.model.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * token测试
 *
 * @author haoxin
 * @date 2021-02-25
 **/
@Slf4j
@DisplayName("token测试")
class TokenTest {

    @Test
    @DisplayName("生成token")
    void create() {
        Token token = Token.create("11");
        assertNotNull(token);
        log.info(token.toString());
    }
}