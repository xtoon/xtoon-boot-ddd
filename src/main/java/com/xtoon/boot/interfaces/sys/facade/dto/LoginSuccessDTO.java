package com.xtoon.boot.interfaces.sys.facade.dto;

import java.io.Serializable;

/**
 * 登录成功DTO
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public class LoginSuccessDTO implements Serializable {

    public LoginSuccessDTO(String token, String expire, String tenantId) {
        this.token = token;
        this.expire = expire;
        this.tenantId = tenantId;
    }

    /**
     * token
     */
    private String token;

    /**
     * 过去时间
     */
    private String expire;

    /**
     * 当前租户id
     */
    private String tenantId;

    public String getToken() {
        return token;
    }

    public String getExpire() {
        return expire;
    }

    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String toString() {
        return "LoginSuccessDTO{" +
                "token='" + token + '\'' +
                ", expire='" + expire + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
