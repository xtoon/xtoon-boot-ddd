package com.xtoon.boot.domain.repository;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.PermissionId;
import com.xtoon.boot.domain.model.user.types.PermissionName;
import com.xtoon.boot.domain.model.user.types.RoleCode;

import java.util.List;
import java.util.Map;

/**
 * 权限-Repository接口
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public interface PermissionRepository {

    /**
     * 查找菜单
     *
     * @param params
     * @return
     */
    List<Permission> queryList(Map<String, Object> params);

    /**
     * 角色编码获取权限
     *
     * @param rolecode
     * @return
     */
    List<Permission> queryList(RoleCode rolecode);

    /**
     * 获取权限
     *
     * @param permissionId
     * @return
     */
    Permission find(PermissionId permissionId);

    /**
     * 获取权限
     *
     * @param permissionName
     * @return
     */
    Permission find(PermissionName permissionName);

    /**
     * 保存
     *
     * @param permission
     */
    void store(Permission permission);

    /**
     * 删除
     *
     * @param permissionId
     */
    void delete(PermissionId permissionId);
}
