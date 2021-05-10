package com.xtoon.boot.sys.application;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.sys.application.dto.UserDTO;

import java.util.Map;

/**
 * 用户查询服务接口
 *
 * @author haoxin
 * @date 2021-05-10
 **/
public interface UserQueryService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 通过用户ID获取用户
     *
     * @param userId
     * @return
     */
    UserDTO find(String userId);

    /**
     * token查询用户
     *
     * @param token
     * @return
     */
    UserDTO queryByToken(String token);
}
