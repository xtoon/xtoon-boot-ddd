package com.xtoon.boot.sys.domain.specification;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.role.Role;
import com.xtoon.boot.sys.domain.model.role.RoleCode;
import com.xtoon.boot.sys.domain.model.role.RoleName;
import com.xtoon.boot.sys.domain.model.role.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 角色创建Specification测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("角色创建Specification测试")
class RoleCreateSpecificationTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("校验")
    @Transactional
    void isSatisfiedBy() {
        Role role = new Role(null, new RoleCode("test11"),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        roleRepository.store(role);
        RoleCreateSpecification roleCreateSpecification = new RoleCreateSpecification(roleRepository);
        assertThrows(RuntimeException.class, () -> {
            roleCreateSpecification.isSatisfiedBy(role);
        });
    }
}