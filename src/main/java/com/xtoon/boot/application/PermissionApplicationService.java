package com.xtoon.boot.application;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.PermissionId;

/**
 * 权限Service
 *
 * @author haoxin
 * @date 2021-02-17
 **/
public interface PermissionApplicationService {

    /**
     * 保存
     *
     * @param permission
     *
     */
    void saveOrUpdate(Permission permission);

    /**
     * 删除
     *
     * @param permissionId
     */
    void delete(PermissionId permissionId);

    /**
     * 禁用
     *
     * @param permissionId
     */
    void disable(PermissionId permissionId);
}
