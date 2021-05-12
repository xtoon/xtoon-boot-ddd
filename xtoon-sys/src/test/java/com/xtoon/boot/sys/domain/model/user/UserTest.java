package com.xtoon.boot.sys.domain.model.user;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.external.TokenGeneratorExternalService;
import com.xtoon.boot.sys.domain.model.role.RoleId;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserTest
 *
 * @author haoxin
 * @date 2021-04-04
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("用户测试")
class UserTest {

    @Autowired
    private TokenGeneratorExternalService tokenGeneratorExternalService;

    User user;

    @BeforeEach
    void setUp() {
        Password password = Password.create("123456","11111");
        Account account = new Account(new Mobile("18555555555"),new Email("2323@qq.com"),password);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        user = new User(new UserId("1"), new UserName("test"), StatusEnum.ENABLE, account, new TenantId("2"), roles);
    }

    @Test
    @DisplayName("是否有效")
    void isEnable() {
        assertTrue(user.isEnable());
    }

    @Test
    @DisplayName("是否相等")
    void sameIdentityAs() {
        User user2 = new User(new UserId("1"), null,null, null, null, null);
        assertTrue(user.sameIdentityAs(user2));
    }

    @Test
    @DisplayName("禁用")
    void disable() {
        StatusEnum statusEnum = user.getStatus();
        user.disable();
        assertFalse(statusEnum.sameValueAs(user.getStatus()));
    }

    @Test
    @DisplayName("刷新token")
    void refreshToken() {
        user.refreshToken(tokenGeneratorExternalService.generateValue());
        assertNotNull(user.getAccount().getToken());
    }

    @Test
    @DisplayName("修改密码")
    void changePassword() {
        user.changePassword("123456","654321");
        assertTrue(user.getAccount().getPassword().sameValueAs(Password.create("654321","11111")));
    }
}