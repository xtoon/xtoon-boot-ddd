package com.xtoon.boot.sys.domain.model.user;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * 密码
 *
 * @author haoxin
 * @date 2021-02-08
 **/
public class Password implements ValueObject<Password> {

    public static final String DEFAULT = "123456";

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    public Password(String password, String salt) {
        if(StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        this.password = password;
        this.salt = salt;
    }

    public static Password create(String passwordStr) {
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = new Sha256Hash(passwordStr, salt).toHex();
        return new Password(password, salt);
    }

    public static Password create(String passwordStr, String salt) {
        if(passwordStr.length() < 6) {
            throw new IllegalArgumentException("密码长度不能小于6");
        }
        String password = new Sha256Hash(passwordStr, salt).toHex();
        return new Password(password, salt);
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean sameValueAs(Password other) {
        return other != null && this.password.equals(other.password);
    }

    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
