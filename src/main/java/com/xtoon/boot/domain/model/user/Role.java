package com.xtoon.boot.domain.model.user;

import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.model.user.types.RoleName;
import com.xtoon.boot.domain.shared.Entity;
import com.xtoon.boot.domain.shared.StatusEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 *
 * @author haoxin
 * @date 2021-02-08
 **/
public class Role implements Entity<Role> {

    /**
     * roleId
     */
    private RoleId roleId;

    /**
     * 角色编码
     */
    private RoleCode roleCode;

    /**
     * 角色名称
     */
    private RoleName roleName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 权限列表
     */
    private List<Permission> permissions;

    public Role(RoleId roleId) {
        this.roleId = roleId;
    }

    public Role(RoleId roleId, RoleCode roleCode, RoleName roleName, String remarks, StatusEnum status, List<Permission> permissions) {
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.remarks = remarks;
        this.status = status;
        this.permissions = permissions;
    }

    public Role(RoleCode roleCode, RoleName roleName, List<Permission> permissions) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public List<String> getPermissionIds() {
        if(permissions == null || permissions.isEmpty()) {
            return null;
        }
        List<String> ids = new ArrayList<>();
        for(Permission permission : permissions) {
            ids.add(permission.getPermissionId().getId());
        }
        return ids;
    }

    /**
     * 禁用
     */
    public void disable() {
        StatusEnum status = this.status == StatusEnum.DISABLE?StatusEnum.ENABLE:StatusEnum.DISABLE;
        this.status = status;
    }

    @Override
    public boolean sameIdentityAs(Role other) {
        return other != null && roleId.sameValueAs(other.roleId);
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public RoleCode getRoleCode() {
        return roleCode;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public String getRemarks() {
        return remarks;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setRoleId(RoleId roleId) {
        this.roleId = roleId;
    }
}
