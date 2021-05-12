package com.xtoon.boot.sys.domain.service;

import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.captcha.Captcha;
import com.xtoon.boot.sys.domain.model.captcha.CaptchaCode;
import com.xtoon.boot.sys.domain.model.captcha.CaptchaRepository;
import com.xtoon.boot.sys.domain.model.captcha.Uuid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 验证码服务测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("验证码服务测试")
class CaptchaValidateServiceTest {

    @Autowired
    private CaptchaRepository captchaRepository;

    @Test
    @Transactional
    @DisplayName("验证码校验")
    void validate() {
        String uuid = "123456";
        String code = "abcd";
        captchaRepository.store(Captcha.createCaptcha(new Uuid(uuid),new CaptchaCode(code)));
        CaptchaValidateService captchaValidateService = new CaptchaValidateService(captchaRepository);
        assertTrue(captchaValidateService.validate(new Uuid(uuid), new CaptchaCode(code)));
    }
}