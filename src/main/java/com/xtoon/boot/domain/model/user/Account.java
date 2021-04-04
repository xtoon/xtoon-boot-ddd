package com.xtoon.boot.domain.model.user;

import com.xtoon.boot.domain.model.types.*;
import com.xtoon.boot.domain.shared.Entity;

/**
 * 账号
 *
 * @author haoxin
 * @date 2021-02-21
 **/
public class Account implements Entity<Account> {

    /**
     * accountId
     */
    private AccountId accountId;

    /**
     * 手机号
     */
    private Mobile mobile;

    /**
     * 邮箱
     */
    private Email email;

    /**
     * 密码
     */
    private Password password;

    /**
     * token
     */
    private Token token;


    public Account(AccountId accountId, Mobile mobile, Email email, Password password, Token token) {
        this.accountId = accountId;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public Account(Mobile mobile, String password) {
        this.mobile = mobile;
        this.password = Password.create(password);
    }

    public Account(Mobile mobile, Email email, Password password) {
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean sameIdentityAs(Account other) {
        return other != null && accountId.sameValueAs(other.accountId);
    }

    /**
     * 密码是否正确
     *
     * @param passwordStr
     * @return
     */
    public boolean checkPassword(String passwordStr) {
        return password != null && this.password.sameValueAs(Password.create(passwordStr,password.getSalt()));
    }

    /**
     * 修改密码
     *
     * @param oldPasswordStr
     * @param newPasswordStr
     * @return
     */
    public void changePassword(String oldPasswordStr,String newPasswordStr) {
        if(!checkPassword(oldPasswordStr)) {
            throw new RuntimeException("原密码不正确");
        }
        this.password = Password.create(newPasswordStr,password.getSalt());
    }

    /**
     * 检查token是否有效
     * @return
     */
    public boolean isTokenValid() {
        return this.token != null && this.token.getExpireTime()!= null &&
                this.token.getExpireTime().getTime() >= System.currentTimeMillis();
    }

    /**
     * 更新Token
     */
    public Account updateToken() {
        this.token = Token.create();
        return this;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Token getToken() {
        return token;
    }
}
