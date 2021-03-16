package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.shared.AbstractSpecification;

/**
 * 用户名登录Specification
 *
 * @author haoxin
 * @date 2021-02-20
 **/
public class LoginByAccountSpecification extends AbstractSpecification<Account> {

    private String password;

    public LoginByAccountSpecification(String password) {
        this.password = password;
    }


    @Override
    public boolean isSatisfiedBy(Account account) {
        if(account == null) {
            throw new IllegalArgumentException("用户或密码不正确");
        }
        if(!account.checkPassword(password)) {
            throw new IllegalArgumentException("用户或密码不正确");
        }
        if(account.getLoginTenantId() == null) {
            throw new IllegalArgumentException("尚未绑定租户,请联系管理员");
        }
        return true;
    }
}
