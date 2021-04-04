package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.model.Role;
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
        if(role.getRoleCode().isTenantAdmin()) {
            throw new RuntimeException("租户管理角色无法修改");
        }
        return true;
    }
}
