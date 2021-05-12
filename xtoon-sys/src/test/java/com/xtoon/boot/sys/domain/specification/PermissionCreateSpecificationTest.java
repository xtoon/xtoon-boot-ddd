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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 权限创建Specification
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("权限创建Specification测试")
class PermissionCreateSpecificationTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @DisplayName("校验")
    @Transactional
    void isSatisfiedBy() {
        Permission permission1 = new Permission(new PermissionId("111"), new PermissionName("用户管理11"), PermissionTypeEnum.CATALOG, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                null, StatusEnum.ENABLE,null);
        Permission permission2 = new Permission(null, new PermissionName("用户管理22"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                permission1, StatusEnum.ENABLE,null);
        PermissionCreateSpecification permissionCreateSpecification = new PermissionCreateSpecification(permissionRepository);
        assertDoesNotThrow( () -> {
            permissionCreateSpecification.isSatisfiedBy(permission2);
        });
    }
}