package com.xtoon.boot.web.org.command;

import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 组织机构Command
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Data
@ApiModel(value="机构",description="机构")
public class OfficeCommand {

    /**
     * id
     */
    @ApiModelProperty(value = "机构id")
    @NotBlank(message="组织结构id不能为空", groups = UpdateGroup.class)
    private String id;

    /**
     * 组织结构编码
     */
    @ApiModelProperty(value = "机构编码")
    @NotBlank(message="机构编码不能为空", groups = AddGroup.class)
    private String officeCode;

    /**
     * 组织结构名称
     */
    @ApiModelProperty(value = "机构名称")
    @NotBlank(message="机构名称不能为空", groups = AddGroup.class)
    private String officeName;

    /**
     * 组织结构类型
     */
    @ApiModelProperty(value = "机构类型")
    @NotBlank(message="机构类型不能为空", groups = AddGroup.class)
    private String officeType;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @NotBlank(message="排序不能为空", groups = AddGroup.class)
    private int orderNum;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    @NotBlank(message="父级ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String parentId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}
