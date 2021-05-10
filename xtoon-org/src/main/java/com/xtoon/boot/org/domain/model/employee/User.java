package com.xtoon.boot.org.domain.model.employee;

import com.xtoon.boot.common.domain.ValueObject;

/**
 * 用户
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class User implements ValueObject<User> {

    /**
     * 手机号
     */
    private Mobile mobile;

    /**
     * 邮箱
     */
    private Email email;

    public User(Mobile mobile, Email email) {
        this.mobile = mobile;
        this.email = email;
    }

    @Override
    public boolean sameValueAs(User other) {
        return other != null && this.mobile.equals(other.mobile);
    }
}
