package com.xtoon.boot.interfaces.sys.web.command;

import lombok.Data;

/**
 * 密码Command
 *
 * @author haoxin
 * @date 2021-02-20
 **/
@Data
public class PasswordCommand {

    /**
     * 原密码
     */
    private String password;

    /**
     * 新密码
     */
    private String newPassword;

}
