package com.xtoon.boot.sys.infrastructure.persistence.repository;

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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 权限RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-02-17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("权限RepositoryImpl测试类")
class PermissionRepositoryImplTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @Transactional
    @DisplayName("搜索")
    void query() {
        Permission permission = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                null, StatusEnum.ENABLE,null);
        permissionRepository.store(permission);
        Map<String ,Object> param = new HashMap<>();
        param.put("permissionLevel", PermissionLevelEnum.TENANT.getValue());
        List<Permission> list = permissionRepository.queryList(param);
        assertFalse(list.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("通过id查询")
    void find() {
        Permission permission = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                null, StatusEnum.ENABLE,null);
        PermissionId permissionId = permissionRepository.store(permission);
        permission = permissionRepository.find(permissionId);
        assertNotNull(permission);
    }

    @Test
    @Transactional
    @DisplayName("保存")
    void store() {
        Permission parent = permissionRepository.find(new PermissionId("1362006127461568513"));
        Set<String> perms = new HashSet<>();
        perms.add("a");
        perms.add("v");
        PermissionCodes permissionCodes = new PermissionCodes(perms);
        Permission permission = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",permissionCodes,0,new MenuUrl("index"),
                parent, StatusEnum.ENABLE,null);
        PermissionId permissionId = permissionRepository.store(permission);
        assertNotNull(permissionId);

    }

    @Test
    @Transactional
    @DisplayName("删除")
    void delete() {
        Permission permission = new Permission(null, new PermissionName("用户管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.TENANT,"",null,0,new MenuUrl("index"),
                null, StatusEnum.ENABLE,null);
        PermissionId permissionId = permissionRepository.store(permission);
        permissionRepository.remove(permissionId);
    }
}