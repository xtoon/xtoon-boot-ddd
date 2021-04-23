package com.xtoon.boot.sys.facade;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.common.util.TenantContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 日志Facade测试类
 *
 * @author haoxin
 * @date 2021-02-06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class LogFacadeServiceTest {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogFacadeService logFacadeService;

    @Test
    void queryPage() {
        TenantContext.setTenantId("3");
        Map<String, Object> params = new HashMap<>();
        Page page = logFacadeService.queryPage(params);
        logger.info(page.toString());
        assertThat(page).isNotNull();
        assertThat(page.getList()).hasSizeGreaterThan(1);
    }
}