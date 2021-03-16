package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.xtoon.boot.domain.factory.TenantFactory;
import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.Mobile;
import com.xtoon.boot.domain.model.user.types.Password;
import com.xtoon.boot.domain.repository.TenantRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 租户Repository测试类
 *
 * @author haoxin
 * @date 2021-02-15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class TenantRepositoryImplTest {

    @Autowired
    private TenantFactory tenantFactory;

    @Autowired
    private TenantRepository tenantRepository;

}