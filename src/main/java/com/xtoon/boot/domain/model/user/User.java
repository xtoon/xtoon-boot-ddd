package com.xtoon.boot.domain.model.user;

import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.user.types.UserId;
import com.xtoon.boot.domain.model.user.types.UserName;
import com.xtoon.boot.domain.shared.Entity;
import com.xtoon.boot.domain.shared.StatusEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public class User implements Entity<User> {

    /**
     * UserId
     */
    private UserId userId;

    /**
     * 用户名
     */
    private UserName userName;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 当前租户
     */
    private Tenant tenant;

    /**
     * 账号
     */
    private Account account;

    /**
     * 角色列表
     */
    private List<Role> roles;


    public User(UserId userId, UserName userName, StatusEnum status,Tenant tenant, List<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.tenant = tenant;
        this.roles = roles;
    }

    public User(UserName userName, Account account, List<Role> roles) {
        this.userName = userName;
        this.account = account;
        this.roles = roles;
        this.status = StatusEnum.ENABLE;
    }

    /**
     * 是否有效
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }

    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userId.sameValueAs(other.userId);
    }

    /**
     * 获取用户权限编码
     *
     * @return
     */
    public Set<String> getUserPermissionCodes() {
        List<Permission> permissions = getUserPermissions();
        if(permissions == null) {
            return null;
        }
        Set<String> permsSet = new HashSet<>();
        for(Permission permission : permissions){
            if(permission.getPermissionCodes() != null){
                permsSet.addAll(permission.getPermissionCodes().getCodes());
            }
        }
        return permsSet;
    }

    /**
     * 获取用户权限ID
     *
     * @return
     */
    public List<String> getUserPermissionIds() {
        List<Permission> permissions = getUserPermissions();
        if(permissions == null) {
            return null;
        }
        List<String> ids = new ArrayList<>();
        for(Permission permission : permissions){
            ids.add(permission.getPermissionId().getId());
        }
        return ids;
    }

    /**
     * 获取权限
     *
     * @return
     */
    private List<Permission> getUserPermissions() {
        if(roles == null) {
            return null;
        }
        List<Permission> permissions = new ArrayList<>();
        for(Role role : roles) {
            if(role.getPermissions() !=null && !role.getPermissions().isEmpty()) {
                permissions.addAll(role.getPermissions());
            }
        }
        return permissions;
    }

    /**
     * 获取用户角色ID
     *
     * @return
     */
    public List<String> getUserRoleIds() {
        if(roles == null) {
            return null;
        }
        List<String> ids = new ArrayList<>();
        for(Role role : roles){
            ids.add(role.getRoleId().getId());
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

    public UserId getUserId() {
        return userId;
    }

    public UserName getUserName() {
        return userName;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Account getAccount() {
        return account;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
