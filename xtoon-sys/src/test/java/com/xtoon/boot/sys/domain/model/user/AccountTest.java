package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 账号测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("账号测试")
class AccountTest {

    static Account account;

    static Password password;

    @BeforeAll
    static void setUp() {
        password = Password.create("123456","abc");
        Token token = Token.create("11");
        account = new Account(new AccountId("1"), new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
    }

    @Test
    @DisplayName("密码验证")
    void checkPassword() {
        assertTrue(account.checkPassword("123456"));
    }

    @Test
    @DisplayName("修改密码")
    void changePassword() {
        account.changePassword("123456","654321");
        assertTrue(account.checkPassword("654321"));
    }

    @Test
    @DisplayName("验证token是否有效")
    void isTokenValid() {
        assertTrue(account.isTokenValid());
    }

    @Test
    @DisplayName("更新token")
    void updateToken() {
        Token token = Token.create("222");
        account.updateToken("222");
        assertTrue(account.getToken().sameValueAs(token));
    }
}