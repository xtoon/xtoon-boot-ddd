package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.*;

import java.util.List;

/**
 * 用户Service
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public interface UserService {

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
    void addUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList);

}
