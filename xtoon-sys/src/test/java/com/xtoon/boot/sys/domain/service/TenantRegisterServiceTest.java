package com.xtoon.boot.sys.domain.service;

import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.application.command.RegisterTenantCommand;
import com.xtoon.boot.sys.domain.model.permission.PermissionRepository;
import com.xtoon.boot.sys.domain.model.role.RoleRepository;
import com.xtoon.boot.sys.domain.model.tenant.TenantCode;
import com.xtoon.boot.sys.domain.model.tenant.TenantName;
import com.xtoon.boot.sys.domain.model.tenant.TenantRepository;
import com.xtoon.boot.sys.domain.model.user.Mobile;
import com.xtoon.boot.sys.domain.model.user.Password;
import com.xtoon.boot.sys.domain.model.user.UserName;
import com.xtoon.boot.sys.domain.model.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 租户注册测试
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("租户注册测试")
class TenantRegisterServiceTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    @Transactional
    @DisplayName("租户注册")
    void registerTenant() {
        RegisterTenantCommand registerTenantCommand = new RegisterTenantCommand();
        registerTenantCommand.setTenantName("测试租户");
        registerTenantCommand.setTenantCode("test");
        registerTenantCommand.setMobile("18666677678");
        registerTenantCommand.setPassword("123456");
        registerTenantCommand.setUserName("测试人");
        TenantRegisterService tenantRegisterService = new TenantRegisterService(tenantRepository, roleRepository, permissionRepository,userRepository);
        assertDoesNotThrow( () -> {
            tenantRegisterService.registerTenant(new TenantName(registerTenantCommand.getTenantName()), new TenantCode(registerTenantCommand.getTenantCode()),new Mobile(registerTenantCommand.getMobile()),
                    Password.create(registerTenantCommand.getPassword()), new UserName(registerTenantCommand.getUserName()));
        });

    }
}