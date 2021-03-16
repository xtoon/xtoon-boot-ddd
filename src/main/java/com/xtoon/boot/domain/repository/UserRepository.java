package com.xtoon.boot.domain.repository;

import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.Token;
import com.xtoon.boot.domain.model.user.types.UserId;

import java.util.List;

/**
 * 用户-Repository接口
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public interface UserRepository {

    /**
     * 通过用户ID获取用户
     *
     * @param userId
     * @return
     */
    User find(UserId userId);

    /**
     * 根据token获取用户
     *
     * @param token
     * @return
     */
    User find(Token token);

    /**
     * 保存
     *
     * @param user
     */
    void store(User user);

    /**
     * 更新
     *
     * @param user
     */
    void update(User user);

    /**
     * 删除
     *
     * @param userIds
     */
    void delete(List<UserId> userIds);
}
