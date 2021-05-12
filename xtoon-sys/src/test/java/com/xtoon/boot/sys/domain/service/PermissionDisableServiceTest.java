package com.xtoon.boot.sys.domain.service;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.permission.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * 权限禁用服务测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("权限禁用服务测试")
class PermissionDisableServiceTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @DisplayName("权限禁用")
    @Transactional
    void disable() {
        Permission permission1 = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                null, StatusEnum.ENABLE,null);
        PermissionId permissionId1 = permissionRepository.store(permission1);
        permission1 = permissionRepository.find(permissionId1);
        Permission permission2 = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                permission1, StatusEnum.ENABLE,null);
        permissionRepository.store(permission2);
        PermissionDisableService permissionDisableService = new PermissionDisableService(permissionRepository);
        permissionDisableService.disable(permissionId1);
        Permission permission3 = permissionRepository.find(permissionId1);
        assertEquals(permission3.getStatus(), StatusEnum.DISABLE);
        assertFalse(permission3.getSubList().isEmpty());
        assertEquals(permission3.getSubList().get(0).getStatus(), StatusEnum.DISABLE);
    }
}