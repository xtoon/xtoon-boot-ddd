package com.xtoon.boot.application.impl;

import com.xtoon.boot.application.PermissionService;
import com.xtoon.boot.domain.factory.PermissionFactory;
import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.PermissionId;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.domain.specification.PermissionDeleteSpecification;
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
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionFactory permissionFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Permission permission, PermissionId parentPermissionId) {
        permissionFactory.createPermission(permission,parentPermissionId);
        permissionRepository.store(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(PermissionId permissionId) {
        PermissionDeleteSpecification permissionDeleteSpecification = new PermissionDeleteSpecification(permissionRepository);
        permissionDeleteSpecification.isSatisfiedBy(permissionId);
        permissionRepository.delete(permissionId);
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
