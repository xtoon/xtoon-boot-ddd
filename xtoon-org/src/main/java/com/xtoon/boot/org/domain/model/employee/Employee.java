package com.xtoon.boot.org.domain.model.employee;

import com.xtoon.boot.common.domain.Entity;
import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.org.domain.model.office.OfficeId;
import com.xtoon.boot.org.domain.model.post.PostId;

import java.util.List;

/**
 * 员工
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class Employee implements Entity<Employee> {

    /**
     * 员工ID
     */
    private EmployeeId employeeId;

    /**
     * 员工姓名
     */
    private EmployeeName employeeName;

    /**
     * 员工工号
     */
    private EmployeeNo employeeNo;

    /**
     * 员工性别
     */
    private SexEnum employeeSex;

    /**
     * 用户
     */
    private User user;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 所属岗位
     */
    private List<PostId> postIds;

    /**
     * 所属部门
     */
    private OfficeId officeId;

    /**
     * 状态
     */
    private StatusEnum status;

    public Employee(EmployeeId employeeId, EmployeeName employeeName, EmployeeNo employeeNo, SexEnum employeeSex, User user, String remarks, List<PostId> postIds, OfficeId officeId, StatusEnum status) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeNo = employeeNo;
        this.employeeSex = employeeSex;
        this.user = user;
        this.remarks = remarks;
        this.postIds = postIds;
        this.officeId = officeId;
        this.status = status;
    }

    @Override
    public boolean sameIdentityAs(Employee other) {
        return other != null && employeeId.sameValueAs(other.employeeId);
    }
}
