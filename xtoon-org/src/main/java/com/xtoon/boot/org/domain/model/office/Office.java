package com.xtoon.boot.org.domain.model.office;

import com.xtoon.boot.common.domain.Entity;
import com.xtoon.boot.common.domain.StatusEnum;

/**
 * 机构
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class Office implements Entity<Office> {

    /**
     * 机构ID
     */
    private OfficeId officeId;

    /**
     * 机构编码
     */
    private OfficeCode officeCode;

    /**
     * 机构名称
     */
    private OfficeName officeName;

    /**
     * 机构类型
     */
    private String officeType;

    /**
     * 父机构ID
     */
    private OfficeId parentOfficeId;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private StatusEnum status;

    public Office(OfficeId officeId, OfficeCode officeCode, OfficeName officeName, String officeType, OfficeId parentOfficeId, int orderNum, String remarks, StatusEnum status) {
        this.officeId = officeId;
        this.officeCode = officeCode;
        this.officeName = officeName;
        this.officeType = officeType;
        this.parentOfficeId = parentOfficeId;
        this.orderNum = orderNum;
        this.remarks = remarks;
        this.status = status;
    }

    @Override
    public boolean sameIdentityAs(Office other) {
        return other != null && officeId.sameValueAs(other.officeId);
    }

    public OfficeId getOfficeId() {
        return officeId;
    }

    public OfficeCode getOfficeCode() {
        return officeCode;
    }

    public OfficeName getOfficeName() {
        return officeName;
    }

    public String getOfficeType() {
        return officeType;
    }

    public OfficeId getParentOfficeId() {
        return parentOfficeId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
