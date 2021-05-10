package com.xtoon.boot.web.org.command;

import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 岗位Command
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Data
@ApiModel(value="岗位",description="岗位")
public class PostCommand {

    /**
     * id
     */
    @ApiModelProperty(value = "岗位id")
    @NotBlank(message="岗位id不能为空", groups = UpdateGroup.class)
    private String id;

    /**
     * 岗位编码
     */
    @ApiModelProperty(value = "岗位编码")
    @NotBlank(message="岗位编码不能为空", groups = AddGroup.class)
    private String postCode;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    @NotBlank(message="岗位名称不能为空", groups = AddGroup.class)
    private String postName;

    /**
     * 岗位类型
     */
    @ApiModelProperty(value = "岗位类型")
    @NotBlank(message="岗位类型不能为空", groups = AddGroup.class)
    private String postType;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @NotBlank(message="排序不能为空", groups = AddGroup.class)
    private int orderNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}
