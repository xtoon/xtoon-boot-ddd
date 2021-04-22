package com.xtoon.boot.sys.application;

import com.xtoon.boot.sys.domain.model.Permission;
import com.xtoon.boot.sys.domain.model.types.PermissionId;

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
