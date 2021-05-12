package com.xtoon.boot.sys.infrastructure.persistence.repository;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.common.util.TenantContext;
import com.xtoon.boot.common.util.mybatis.MybatisPlusConfig;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.role.RoleId;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import com.xtoon.boot.sys.domain.model.user.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 用户RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("用户RepositoryImpl测试类")
@ContextConfiguration(classes = MybatisPlusConfig.class)
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    UserId userId;

    @BeforeAll
    static void setUp() {
        TenantContext.setTenantId("1");
    }

    @Test
    @Transactional
    @DisplayName("通过用户ID查找")
    void find() {
        Password password = Password.create("123456","11111");
        Token token = Token.create("1122");
        Account account = new Account(null, new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(null, new UserName("test"), StatusEnum.ENABLE, account, new TenantId("1"), roles);
        UserId userId = userRepository.store(user);
        assertNotNull(userRepository.find(userId));
    }

    @Test
    @Transactional
    @DisplayName("通过token查找")
    void find1() {
        Password password = Password.create("123456","11111");
        Token token = Token.create("1122");
        Account account = new Account(null, new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(null, new UserName("test"), StatusEnum.ENABLE, account, new TenantId("1"), roles);
        userRepository.store(user);
        assertNotNull(userRepository.find(token));
    }

    @Test
    @Transactional
    @DisplayName("通过手机号查找")
    void find2() {
        Password password = Password.create("123456","11111");
        Token token = Token.create("1122");
        Account account = new Account(null, new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(null, new UserName("test"), StatusEnum.ENABLE, account, new TenantId("1"), roles);
        userRepository.store(user);
        assertNotNull(userRepository.find(new Mobile("18666656565")));
    }

    @Test
    @Transactional
    @DisplayName("保存")
    void store() {
        Password password = Password.create("123456","11111");
        Token token = Token.create("1122");
        Account account = new Account(null, new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(null, new UserName("test"), StatusEnum.ENABLE, account, new TenantId("1"), roles);
        UserId userId = userRepository.store(user);
        assertNotNull(userId);
    }

    @Test
    @Transactional
    @DisplayName("删除")
    void remove() {
        Password password = Password.create("123456","11111");
        Token token = Token.create("1122");
        Account account = new Account(null, new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(null, new UserName("test"), StatusEnum.ENABLE, account, new TenantId("1"), roles);
        UserId userId = userRepository.store(user);
        List<UserId> userIds = new ArrayList<>();
        userIds.add(userId);
        userRepository.remove(userIds);
        assertNull(userRepository.find(userId));
    }
}