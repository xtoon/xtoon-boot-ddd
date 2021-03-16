package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.shared.AbstractSpecification;

/**
 * 角色修改Specification
 *
 * @author haoxin
 * @date 2021-02-27
 **/
public class RoleUpdateSpecification extends AbstractSpecification<Role> {

    @Override
    public boolean isSatisfiedBy(Role role) {
        if(RoleCode.SYS_ADMIN.equals(role.getRoleCode().getCode())) {
            throw new RuntimeException("系统管理角色无法修改");
        }
        if(RoleCode.TENANT_ADMIN.equals(role.getRoleCode().getCode())) {
            throw new RuntimeException("租户管理角色无法修改");
        }
        return true;
    }
}
