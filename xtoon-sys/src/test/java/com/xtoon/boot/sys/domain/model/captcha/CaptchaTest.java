package com.xtoon.boot.sys.domain.model.captcha;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 验证码验证测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("验证码验证测试")
class CaptchaTest {

    @Test
    @DisplayName("验证码校验")
    void validate() {
        Captcha captcha = new Captcha(new Uuid("1111"), new CaptchaCode("332"),new Date());
        assertTrue(captcha.validate(new CaptchaCode("332")));
    }
}