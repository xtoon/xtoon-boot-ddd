package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.PermissionId;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.domain.shared.AbstractSpecification;

/**
 * 权限创建Specification
 *
 * @author haoxin
 * @date 2021-02-20
 **/
public class PermissionDeleteSpecification extends AbstractSpecification<PermissionId> {

    private PermissionRepository permissionRepository;

    public PermissionDeleteSpecification(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public boolean isSatisfiedBy(PermissionId permissionId) {
        Permission permission = permissionRepository.find(permissionId);
        if(permission.hasSub()) {
            throw new RuntimeException("请先删除子菜单或按钮");
        }
        return true;
    }
}
