package com.xtoon.boot.interfaces.sys.facade;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.shared.Page;
import com.xtoon.boot.interfaces.sys.facade.dto.UserDTO;

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
     * 修改密码
     *
     * @param account
     * @param oldPasswordStr
     * @param newPasswordStr
     */
    void changePassword(Account account, String oldPasswordStr, String newPasswordStr);

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
     * 注册用户
     *
     * @param tenantId
     * @param roleId
     * @param mobile
     * @param userName
     */
    void registerUser(String tenantId, String roleId, String mobile, String userName);
}
