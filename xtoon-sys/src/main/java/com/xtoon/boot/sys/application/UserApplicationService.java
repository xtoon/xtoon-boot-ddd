package com.xtoon.boot.sys.application;

import com.xtoon.boot.sys.application.dto.UserDTO;

import java.util.List;

/**
 * 用户应用服务接口
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public interface UserApplicationService {

    /**
     * 保存用户
     *
     * @param userDTO
     */
    void save(UserDTO userDTO);

    /**
     * 更新用户
     *
     * @param userDTO
     */
    void update(UserDTO userDTO);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPasswordStr
     * @param newPasswordStr
     */
    void changePassword(String userId, String oldPasswordStr, String newPasswordStr);
}
