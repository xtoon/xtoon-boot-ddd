package com.xtoon.boot.org.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 机构DTO
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Data
public class OfficeDTO implements Serializable {

    public OfficeDTO(String id, String officeCode, String officeName, String officeType, int orderNum, String parentId, String remarks) {
        this.id = id;
        this.officeCode = officeCode;
        this.officeName = officeName;
        this.officeType = officeType;
        this.orderNum = orderNum;
        this.parentId = parentId;
        this.remarks = remarks;
    }

    /**
     * id
     */
    private String id;

    /**
     * 组织结构编码
     */
    private String officeCode;

    /**
     * 组织结构名称
     */
    private String officeName;

    /**
     * 组织结构类型
     */
    private String officeType;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 备注
     */
    private String remarks;
}
