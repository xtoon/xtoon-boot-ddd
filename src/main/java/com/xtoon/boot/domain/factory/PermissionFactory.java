package com.xtoon.boot.domain.factory;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.PermissionId;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.domain.specification.PermissionCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 权限Factory
 *
 * @author haoxin
 * @date 2021-02-20
 **/
@Component
public class PermissionFactory {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission, PermissionId parentPermissionId) {
        Permission parent = permissionRepository.find(parentPermissionId);
        permission.setParent(parent);
        PermissionCreateSpecification permissionCreateSpecification = new PermissionCreateSpecification(permissionRepository);
        permissionCreateSpecification.isSatisfiedBy(permission);
        return permission;
    }

}
