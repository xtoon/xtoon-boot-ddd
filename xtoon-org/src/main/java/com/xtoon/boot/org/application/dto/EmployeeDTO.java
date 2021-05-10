package com.xtoon.boot.org.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 员工DTO
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Data
public class EmployeeDTO implements Serializable {

    public EmployeeDTO(String id, String empNo, String empName, String empSex, String mobile, String email, String officeId, List<String> postIdList, String remarks) {
        this.id = id;
        this.empNo = empNo;
        this.empName = empName;
        this.empSex = empSex;
        this.mobile = mobile;
        this.email = email;
        this.officeId = officeId;
        this.postIdList = postIdList;
        this.remarks = remarks;
    }

    /**
     * id
     */
    private String id;

    /**
     * 员工工号
     */
    private String empNo;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 员工性别
     */
    private String empSex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 机构id
     */
    private String officeId;

    /**
     * 岗位列表
     */
    private List<String> postIdList;

    /**
     * 备注
     */
    private String remarks;
}
