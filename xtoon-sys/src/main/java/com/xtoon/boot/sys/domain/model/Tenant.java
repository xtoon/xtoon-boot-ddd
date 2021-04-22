package com.xtoon.boot.sys.domain.model;

import com.xtoon.boot.common.domain.Entity;
import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.domain.model.types.TenantCode;
import com.xtoon.boot.sys.domain.model.types.TenantId;
import com.xtoon.boot.sys.domain.model.types.TenantName;
import com.xtoon.boot.sys.domain.model.types.UserId;

/**
 * 租户实体
 *
 * @author haoxin
 * @date 2021-02-08
 **/
public class Tenant implements Entity<Tenant> {

    /**
     * TenantId
     */
    private TenantId tenantId;

    /**
     * 租户编码
     */
    private TenantCode tenantCode;

    /**
     * 租户名称
     */
    private TenantName tenantName;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 创建者Id
     */
    private UserId creatorId;

    public Tenant(TenantCode tenantCode, TenantName tenantName) {
        this.tenantCode = tenantCode;
        this.tenantName = tenantName;
        this.status = StatusEnum.ENABLE;
    }

    public Tenant(TenantId tenantId, TenantCode tenantCode, TenantName tenantName, StatusEnum status, UserId creatorId) {
        this.tenantId = tenantId;
        this.tenantCode = tenantCode;
        this.tenantName = tenantName;
        this.status = status;
        this.creatorId = creatorId;
    }

    /**
     * 是否有效
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }

    /**
     * 禁用
     */
    public void disable() {
        StatusEnum status = this.status == StatusEnum.DISABLE?StatusEnum.ENABLE:StatusEnum.DISABLE;
        this.status = status;
    }

    @Override
    public boolean sameIdentityAs(Tenant other) {
        return other != null && tenantId.sameValueAs(other.tenantId);
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public TenantCode getTenantCode() {
        return tenantCode;
    }

    public TenantName getTenantName() {
        return tenantName;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public UserId getCreatorId() {
        return creatorId;
    }
}
