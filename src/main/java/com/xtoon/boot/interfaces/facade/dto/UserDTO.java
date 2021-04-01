package com.xtoon.boot.interfaces.facade.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户DTO
 *
 * @author haoxin
 * @date 2021-02-23
 **/
@Data
public class UserDTO implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * email
     */
    private String email;

    /**
     * mobile
     */
    private String mobile;

    /**
     * status
     */
    private String status;

    /**
     * 角色列表
     */
    private List<String> roleIdList;

    public UserDTO() {
    }

    public UserDTO(String id, String userName, String email, String mobile, String status, List<String> roleIdList) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.roleIdList = roleIdList;
    }
}
