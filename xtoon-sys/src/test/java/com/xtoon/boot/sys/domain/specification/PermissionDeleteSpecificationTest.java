package com.xtoon.boot.sys.domain.specification;

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

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 权限删除Specification测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("权限删除Specification测试")
class PermissionDeleteSpecificationTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @DisplayName("校验")
    @Transactional
    void isSatisfiedBy() {
        Permission permission1 = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                null, StatusEnum.ENABLE,null);
        PermissionId permissionId1 = permissionRepository.store(permission1);
        permission1 = permissionRepository.find(permissionId1);
        Permission permission2 = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                permission1, StatusEnum.ENABLE,null);
        permissionRepository.store(permission2);
        PermissionDeleteSpecification permissionDeleteSpecification = new PermissionDeleteSpecification(permissionRepository);
        assertThrows(RuntimeException.class, () -> {
            permissionDeleteSpecification.isSatisfiedBy(permissionId1);
        });
    }
}