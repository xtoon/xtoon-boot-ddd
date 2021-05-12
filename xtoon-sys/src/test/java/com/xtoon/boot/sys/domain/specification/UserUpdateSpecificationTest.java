package com.xtoon.boot.sys.domain.specification;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.tenant.*;
import com.xtoon.boot.sys.domain.model.user.User;
import com.xtoon.boot.sys.domain.model.user.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 用户修改Specification测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("用户修改Specification测试")
class UserUpdateSpecificationTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    @DisplayName("校验")
    @Transactional
    void isSatisfiedBy() {
        User user = new User(new UserId("111"), null,null, null, new TenantId("99"), null);
        Tenant tenant = new Tenant(new TenantId("99"), new TenantCode("test1"),new TenantName("测试租户1"), StatusEnum.ENABLE,new UserId("111"));
        tenantRepository.store(tenant);
        UserUpdateSpecification userUpdateSpecification = new UserUpdateSpecification(tenantRepository);
        assertThrows(RuntimeException.class, () -> {
            userUpdateSpecification.isSatisfiedBy(user);
        });
    }
}