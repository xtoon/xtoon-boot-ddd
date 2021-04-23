package com.xtoon.boot.sys.facade;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.sys.facade.dto.LoginSuccessDTO;
import com.xtoon.boot.sys.facade.dto.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * 用户Facade
 *
 * @author haoxin
 * @date 2021-02-08
 **/
public interface SysUserServiceFacade {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 账号登录
     *
     * @param accountName
     * @param password
     * @return
     */
    LoginSuccessDTO loginByAccount(String accountName, String password);

    /**
     * 手机号登录
     *
     * @param mobile
     * @return
     */
    LoginSuccessDTO loginByMobile(String mobile);

    /**
     * 登出
     *
     * @param userId
     */
    void logout(String userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPasswordStr
     * @param newPasswordStr
     */
    void changePassword(String userId, String oldPasswordStr, String newPasswordStr);

    /**
     * 通过用户ID获取用户
     *
     * @param userId
     * @return
     */
    UserDTO find(String userId);

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
     * 通过token获取用户
     *
     * @param token
     * @return
     */
    UserDTO queryByToken(String token);

}
