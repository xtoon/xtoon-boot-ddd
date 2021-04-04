package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.types.*;
import com.xtoon.boot.domain.model.user.User;

import java.util.List;

/**
 * 用户Service
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public interface UserApplicationService {

    /**
     * 通过账号密码
     *
     * @param mobile
     * @param password
     * @return
     */
    User login(Mobile mobile, String password);

    /**
     * 通过手机号登录
     *
     * @param mobile
     * @return
     */
    User login(Mobile mobile);

    /**
     * 登出
     *
     * @param userId
     */
    void logout(UserId userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPasswordStr
     * @param newPasswordStr
     */
    void changePassword(UserId userId, String oldPasswordStr, String newPasswordStr);

    /**
     * 更新用户
     *
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     *
     * @param userIds
     */
    void delete(List<UserId> userIds);

    /**
     * 禁用用户
     *
     * @param userId
     */
    void disable(UserId userId);

    /**
     * 添加用户
     *
     * @param mobile
     * @param email
     * @param password
     * @param userName
     * @param roleIdList
     */
    void addUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList, TenantId tenantId);

}
