package com.xtoon.boot.sys.interfaces.web.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 注册用户Command
 *
 * @author haoxin
 * @date 2021-02-24
 **/
@Data
@ApiModel(value="注册用户",description="注册用户")
public class RegisterUserCommand {

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    @NotBlank(message="租户id不能为空")
    private String tenantId;


    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @NotBlank(message="角色id不能为空")
    private String roleId;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    @NotBlank(message="用户名不能为空")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String verificationCode;
}
