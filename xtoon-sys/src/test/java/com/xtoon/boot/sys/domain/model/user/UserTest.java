package com.xtoon.boot.sys.domain.model.user;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.domain.external.TokenGeneratorExternalService;
import com.xtoon.boot.sys.domain.model.role.RoleId;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserTest
 *
 * @author haoxin
 * @date 2021-04-04
 **/
@Slf4j
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
    void isEnable() {
        assertThat(user.isEnable()).isTrue();
    }

    @Test
    void sameIdentityAs() {
        User user2 = new User(new UserId("1"), null,null, null, null, null);
        assertThat(user.sameIdentityAs(user2)).isTrue();
    }

    @Test
    void disable() {
        StatusEnum statusEnum = user.getStatus();
        user.disable();
        assertThat(statusEnum.sameValueAs(user.getStatus())).isFalse();
    }

    @Test
    void refreshToken() {
        user.refreshToken(tokenGeneratorExternalService.generateValue());
        Assertions.assertThat(user.getAccount().getToken()).isNotNull();
    }

    @Test
    void changePassword() {
        log.info(user.getAccount().getPassword().getSalt());
        user.changePassword("123456","654321");
        Assertions.assertThat(user.getAccount().getPassword().sameValueAs(Password.create("654321","11111"))).isTrue();
    }
}