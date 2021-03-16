package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.repository.RoleRepository;
import com.xtoon.boot.domain.shared.AbstractSpecification;

/**
 * 角色创建Specification
 *
 * @author haoxin
 * @date 2021-02-20
 **/
public class RoleCreateSpecification extends AbstractSpecification<Role> {

    private RoleRepository roleRepository;

    public RoleCreateSpecification(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean isSatisfiedBy(Role role) {
        if(role.getRoleCode() != null) {
            Role existRole = roleRepository.find(role.getRoleCode());
            if(existRole != null && !existRole.getRoleId().sameValueAs(role.getRoleId())) {
                throw new IllegalArgumentException("角色编码已存在");
            }
        }
        if(role.getRoleName() != null) {
            Role existRole = roleRepository.find(role.getRoleName());
            if(existRole != null && !existRole.getRoleId().sameValueAs(role.getRoleId())) {
                throw new IllegalArgumentException("角色名称已存在");
            }
        }
        if(RoleCode.SYS_ADMIN.equals(role.getRoleCode().getCode()) || RoleCode.TENANT_ADMIN.equals(role.getRoleCode().getCode())) {
            throw new IllegalArgumentException("管理角色无法更新");
        }
        return true;
    }
}
