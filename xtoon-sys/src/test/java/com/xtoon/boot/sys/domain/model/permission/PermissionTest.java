package com.xtoon.boot.sys.domain.model.permission;

import com.xtoon.boot.common.domain.StatusEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 权限测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("权限测试")
class PermissionTest {

    static Permission permission1;
    static Permission permission2;

    @BeforeAll
    static void setUp() {
        permission1 = new Permission(new PermissionId("1"), new PermissionName("系统管理"), PermissionTypeEnum.MENU, PermissionLevelEnum.SYSTEM,
                "1",null,1,null,null, StatusEnum.ENABLE,null);
        List<Permission> sub = new ArrayList<>();
        sub.add(permission1);
        permission2 = new Permission(new PermissionId("1"), new PermissionName("系统管理"), PermissionTypeEnum.BUTTON, PermissionLevelEnum.SYSTEM,
                "1",null,1,null,null, StatusEnum.ENABLE,sub);
    }

    @Test
    @DisplayName("是否是菜单")
    void isMenu() {
        assertTrue(permission1.isMenu());
        assertFalse(permission2.isMenu());
    }

    @Test
    @DisplayName("是否有子权限")
    void hasSub() {
        assertFalse(permission1.hasSub());
        assertTrue(permission2.hasSub());
    }

    @Test
    @DisplayName("禁用")
    void disable() {
        permission2.disable();
        assertEquals(permission1.getStatus(),StatusEnum.DISABLE);
        assertEquals(permission2.getStatus(),StatusEnum.DISABLE);
    }
}