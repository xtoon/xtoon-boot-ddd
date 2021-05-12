package com.xtoon.boot.sys.domain.specification;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.domain.model.role.Role;
import com.xtoon.boot.sys.domain.model.role.RoleCode;
import com.xtoon.boot.sys.domain.model.role.RoleName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 角色修改Specification测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("角色修改Specification测试")
class RoleUpdateSpecificationTest {

    @Test
    @DisplayName("校验")
    void isSatisfiedBy() {
        Role role = new Role(null, new RoleCode(RoleCode.TENANT_ADMIN),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        RoleUpdateSpecification roleUpdateSpecification = new RoleUpdateSpecification();
        assertThrows(RuntimeException.class, () -> {
            roleUpdateSpecification.isSatisfiedBy(role);
        });
    }
}