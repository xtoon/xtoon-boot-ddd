package com.xtoon.boot.sys.domain.model.types;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * token值对象
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public final class Token implements ValueObject<Token> {

    /**
     * token
     */
    private String token;

    /**
     * 有效周期
     */
    private int expirePeriod;

    /**
     * 过期时间
     */
    private Date expireTime;



    public Token(String token, Date expireTime) {
        if(StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("token不能为空");
        }
        this.token = token;
        this.expireTime = expireTime;
    }

    public Token(String token, Date expireTime, int expirePeriod) {
        if(StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("token不能为空");
        }
        this.token = token;
        this.expireTime = expireTime;
        this.expirePeriod = expirePeriod;
    }

    /**
     * 创建Token
     */
    public static Token create(String tokenStr) {
        //12小时后过期
        int expirePeriod = 3600 * 12;
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + expirePeriod * 1000);
        return new Token(tokenStr,expireTime, expirePeriod);
    }

    public String getToken() {
        return token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public int getExpirePeriod() {
        return expirePeriod;
    }

    @Override
    public boolean sameValueAs(Token other) {
        return other != null && this.token.equals(other.token);
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", expirePeriod=" + expirePeriod +
                ", expireTime=" + expireTime +
                '}';
    }
}
