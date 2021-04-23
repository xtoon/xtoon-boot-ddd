package com.xtoon.boot.sys.facade;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 租户Facade测试类
 *
 * @author haoxin
 * @date 2021-02-15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class TenantFacadeServiceTest {

    @Autowired
    private TenantFacadeService tenantFacadeService;

    @Test
    void registerTenant() {
        tenantFacadeService.registerTenant("京东","JD","","18888888888","2222222222");
    }

}