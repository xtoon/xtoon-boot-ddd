package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.RoleId;

import java.util.List;

/**
 * 角色Service
 *
 * @author haoxin
 * @date 2021-02-17
 **/
public interface RoleApplicationService {

    /**
     * 保存
     *
     * @param role
     */
    void saveOrUpdate(Role role);

    /**
     * 删除
     *
     * @param roleIds
     */
    void delete(List<RoleId> roleIds);

    /**
     * 禁用
     *
     * @param roleId
     */
    void disable(RoleId roleId);
}
