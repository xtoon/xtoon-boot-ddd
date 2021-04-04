package com.xtoon.boot.interfaces.web.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 手机号登录Command
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Data
@ApiModel(value="手机号登录",description="手机号登录")
public class MobileLoginCommand {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String verificationCode;

}
