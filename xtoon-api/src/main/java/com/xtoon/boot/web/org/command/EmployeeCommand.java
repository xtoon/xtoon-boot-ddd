package com.xtoon.boot.web.org.command;

import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 员工Command
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Data
@ApiModel(value="员工",description="员工")
public class EmployeeCommand {

    /**
     * id
     */
    @ApiModelProperty(value = "员工id")
    @NotBlank(message="员工id不能为空", groups = UpdateGroup.class)
    private String id;

    /**
     * 员工工号
     */
    @ApiModelProperty(value = "员工工号")
    @NotBlank(message="员工工号不能为空", groups = AddGroup.class)
    private String empNo;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名")
    @NotBlank(message="员工姓名不能为空", groups = AddGroup.class)
    private String empName;

    /**
     * 员工性别
     */
    @ApiModelProperty(value = "员工性别")
    @NotBlank(message="员工性别不能为空", groups = AddGroup.class)
    private String empSex;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空" , groups = AddGroup.class)
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message="邮箱不能为空" , groups = AddGroup.class)
    private String email;

    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id")
    @NotBlank(message="机构id不能为空")
    private String officeId;

    /**
     * 岗位列表
     */
    @ApiModelProperty(value = "岗位列表")
    private List<String> postIdList;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}
