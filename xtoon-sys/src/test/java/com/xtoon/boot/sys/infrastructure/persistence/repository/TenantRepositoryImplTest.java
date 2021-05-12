package com.xtoon.boot.sys.infrastructure.persistence.repository;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.tenant.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 租户RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("租户RepositoryImpl测试类")
class TenantRepositoryImplTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    @Transactional
    @DisplayName("通过租户ID查找")
    void find() {
        Tenant tenant = new Tenant(new TenantId("1"), new TenantCode("test1"),new TenantName("测试租户1"), StatusEnum.ENABLE,null);
        TenantId tenantId = tenantRepository.store(tenant);
        assertNotNull(tenantRepository.find(tenantId));
    }

    @Test
    @Transactional
    @DisplayName("通过租户名称查找")
    void find1() {
        Tenant tenant = new Tenant(new TenantId("1"), new TenantCode("test1"),new TenantName("测试租户1"), StatusEnum.ENABLE,null);
        tenantRepository.store(tenant);
        assertNotNull(tenantRepository.find(new TenantName("测试租户1")));
    }

    @Test
    @Transactional
    @DisplayName("通过租户编码查找")
    void find2() {
        Tenant tenant = new Tenant(new TenantId("1"), new TenantCode("test1"),new TenantName("测试租户1"), StatusEnum.ENABLE,null);
        tenantRepository.store(tenant);
        assertNotNull(tenantRepository.find(new TenantCode("test1")));
    }

    @Test
    @Transactional
    @DisplayName("保存")
    void store() {
        Tenant tenant = new Tenant(new TenantId("1"), new TenantCode("test1"),new TenantName("测试租户1"), StatusEnum.ENABLE,null);
        TenantId tenantId = tenantRepository.store(tenant);
        assertNotNull(tenantId);
    }
}