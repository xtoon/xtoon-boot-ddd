package com.xtoon.boot.domain.model.user;

import com.xtoon.boot.domain.model.user.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * AccountTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class AccountTest {

    Account account;

    @BeforeEach
    void setUp() {
        account = new Account(new AccountId("1"), new Mobile("18555555555"), new Email("23223@qq.com"),Password.create("123456","11"),
                Token.create(),null);
    }

    @Test
    void sameIdentityAs() {
        Account account1 = new Account(new AccountId("1"), new Mobile("18555555555"), new Email("23223@qq.com"),Password.create("123456","11"),
                Token.create(),null);
        assertThat(account.sameIdentityAs(account1)).isTrue();
    }

    @Test
    void checkPassword() {
        assertThat(account.checkPassword("123456")).isTrue();
    }

    @Test
    void changePassword() {
        assertThrows(RuntimeException.class, () -> {
            account.changePassword("1234567","12345678");
        });
        assertThat(account.changePassword("123456","1234567")).isNotNull();
    }

    @Test
    void isTokenValid() {
        assertThat(account.isTokenValid()).isTrue();
    }

    @Test
    void updateToken() {
        Token token = account.getToken();
        Account account1 = account.updateToken();
        assertThat(token.sameValueAs(account1.getToken())).isFalse();
    }
}