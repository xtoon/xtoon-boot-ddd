package com.xtoon.boot.domain.specification;

import com.xtoon.boot.domain.exception.NoRegisterException;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.shared.AbstractSpecification;

/**
 * 手机号登录Specification
 *
 * @author haoxin
 * @date 2021-02-20
 **/
public class LoginByMobileSpecification extends AbstractSpecification<Account> {

    @Override
    public boolean isSatisfiedBy(Account account) {
        if(account == null) {
            throw new NoRegisterException("用户不存在");
        }
        if(account.getLoginTenantId() == null) {
            throw new IllegalArgumentException("尚未绑定租户,请联系管理员");
        }
        return true;
    }
}
