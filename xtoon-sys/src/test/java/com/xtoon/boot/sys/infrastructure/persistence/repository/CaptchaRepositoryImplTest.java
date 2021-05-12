package com.xtoon.boot.sys.infrastructure.persistence.repository;

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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 验证码RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("验证码RepositoryImpl测试类")
class CaptchaRepositoryImplTest {

    @Autowired
    private CaptchaRepository captchaRepository;

    @Test
    @Transactional
    @DisplayName("通过uuid查找")
    void find() {
        store();
        Captcha captcha = captchaRepository.find(new Uuid("11"));
        assertNotNull(captcha);
    }

    @Test
    @Transactional
    @DisplayName("保存")
    void store() {
        captchaRepository.store(Captcha.createCaptcha(new Uuid("11"),new CaptchaCode("ab")));
    }

    @Test
    @Transactional
    @DisplayName("删除")
    void remove() {
        store();
        captchaRepository.remove(new Uuid("11"));
        Captcha captcha = captchaRepository.find(new Uuid("11"));
        assertNull(captcha);
    }
}