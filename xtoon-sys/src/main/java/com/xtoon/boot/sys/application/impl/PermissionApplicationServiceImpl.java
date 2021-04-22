package com.xtoon.boot.sys.application.impl;

import com.xtoon.boot.sys.application.PermissionApplicationService;
import com.xtoon.boot.sys.domain.model.Permission;
import com.xtoon.boot.sys.domain.model.types.PermissionId;
import com.xtoon.boot.sys.domain.repository.PermissionRepository;
import com.xtoon.boot.sys.domain.specification.PermissionCreateSpecification;
import com.xtoon.boot.sys.domain.specification.PermissionDeleteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限ServiceImpl
 *
 * @author haoxin
 * @date 2021-02-17
 **/
@Service
public class PermissionApplicationServiceImpl implements PermissionApplicationService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Permission permission) {
        PermissionCreateSpecification permissionCreateSpecification = new PermissionCreateSpecification(permissionRepository);
        permissionCreateSpecification.isSatisfiedBy(permission);
        permissionRepository.store(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(PermissionId permissionId) {
        PermissionDeleteSpecification permissionDeleteSpecification = new PermissionDeleteSpecification(permissionRepository);
        permissionDeleteSpecification.isSatisfiedBy(permissionId);
        permissionRepository.remove(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(PermissionId permissionId) {
        Permission permission = permissionRepository.find(permissionId);
        permission.disable();
        permissionRepository.store(permission);
        if(permission.hasSub()) {
            for(Permission subPermission:permission.getSubList()) {
                permissionRepository.store(subPermission);
            }
        }
    }
}
