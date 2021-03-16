package com.xtoon.boot.domain.repository;

import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.model.user.types.RoleName;

import java.util.List;

/**
 * 角色-Repository接口
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public interface RoleRepository {

    /**
     * 获取角色
     *
     * @param roleId
     * @return
     */
    Role find(RoleId roleId);

    /**
     * 获取角色
     *
     * @param roleName
     * @return
     */
    Role find(RoleName roleName);

    /**
     * 获取角色
     *
     * @param roleCode
     * @return
     */
    Role find(RoleCode roleCode);

    /**
     * 保存
     *
     * @param role
     */
    void store(Role role);

    /**
     * 更新
     *
     * @param role
     */
    void update(Role role);

    /**
     * 删除
     *
     * @param roleIds
     */
    void delete(List<RoleId> roleIds);

}
