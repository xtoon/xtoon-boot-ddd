package com.xtoon.boot.org.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 岗位DTO
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Data
public class PostDTO implements Serializable {


    public PostDTO(String id, String postCode, String postName, String postType, int orderNum, String remarks) {
        this.id = id;
        this.postCode = postCode;
        this.postName = postName;
        this.postType = postType;
        this.orderNum = orderNum;
        this.remarks = remarks;
    }

    /**
     * id
     */
    private String id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位类型
     */
    private String postType;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 备注
     */
    private String remarks;
}
