package com.xtoon.boot.sys.infrastructure.persistence.repository;

import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.log.Log;
import com.xtoon.boot.sys.domain.model.log.LogRepository;
import com.xtoon.boot.sys.domain.model.user.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("验证码RepositoryImpl测试类")
class LogRepositoryImplTest {

    @Autowired
    private LogRepository logRepository;

    @Test
    @Transactional
    @DisplayName("保存")
    void store() {
        Log log = new Log(null,new UserName("test"),"save","save","1",988776L,"");
        logRepository.store(log);
    }
}