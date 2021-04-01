package com.xtoon.boot.domain.model.system;

import com.xtoon.boot.domain.shared.Entity;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * 日志实体
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public class Log implements Entity<Log> {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 租户ID
     */
    private String tenantId;

    public Log(String userName, String operation, String method, String params, Long time, String ip, String tenantId) {
        this.userName = userName;
        this.operation = operation;
        this.method = method;
        this.params = params;
        this.time = time;
        this.ip = ip;
        this.tenantId = tenantId;
    }

    @Override
    public boolean sameIdentityAs(Log other) {
        return other != null && StringUtils.isNotBlank(this.id) && StringUtils.isNotBlank(other.id) && this.id.equals(other.id);
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getOperation() {
        return operation;
    }

    public String getMethod() {
        return method;
    }

    public String getParams() {
        return params;
    }

    public Long getTime() {
        return time;
    }

    public String getIp() {
        return ip;
    }

    public String getTenantId() {
        return tenantId;
    }
}
