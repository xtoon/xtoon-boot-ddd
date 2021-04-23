package com.xtoon.boot.web.sys.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 账号名登录Command
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Data
@ApiModel(value="账号登录",description="账号登录")
public class AccountLoginCommand {

    /**
     * 账号名
     */
    @ApiModelProperty(value = "账号名")
    @NotBlank(message="账号名不能为空")
    private String accountName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String captcha;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    @NotBlank(message="uuid不能为空")
    private String uuid;
}
