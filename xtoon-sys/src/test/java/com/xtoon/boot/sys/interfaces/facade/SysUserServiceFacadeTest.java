package com.xtoon.boot.sys.interfaces.facade;

import com.xtoon.boot.sys.facade.SysUserServiceFacade;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 用户Facade测试类
 *
 * @author haoxin
 * @date 2021-02-13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class SysUserServiceFacadeTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserServiceFacade sysUserServiceFacade;

}