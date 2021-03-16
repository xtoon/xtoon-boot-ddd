package com.xtoon.boot.domain.model.user;

import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.domain.shared.Entity;

import java.util.List;

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

    /**
     * 登录的租户
     */
    private TenantId loginTenantId;

    /**
     * 关联用户
     */
    private List<User> users;

    public Account(AccountId accountId, Mobile mobile, Email email, Password password, Token token, List<User> users) {
        this.accountId = accountId;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.token = token;
        this.users = users;
    }

    public Account(Mobile mobile, String password) {
        this.mobile = mobile;
        this.password = Password.create(password);
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
    public Account changePassword(String oldPasswordStr,String newPasswordStr) {
        if(!checkPassword(oldPasswordStr)) {
            throw new RuntimeException("原密码不正确");
        }
        this.password = Password.create(newPasswordStr,password.getSalt());
        return this;
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

    public TenantId getLoginTenantId() {
        return loginTenantId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setAccountId(AccountId accountId) {
        this.accountId = accountId;
    }

    public void setLoginTenantId(TenantId loginTenantId) {
        this.loginTenantId = loginTenantId;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
