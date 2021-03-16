package com.xtoon.boot.domain.factory;

import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.PermissionLevelEnum;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.model.user.types.RoleName;
import com.xtoon.boot.domain.model.user.types.UserName;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.domain.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租户Factory
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Component
public class TenantFactory {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 创建租户
     *
     * @param tenantName
     * @param tenantCode
     * @param userName
     * @param account
     * @return
     */
    public Tenant createTenant(TenantName tenantName, TenantCode tenantCode, UserName userName, Account account) {
        if(tenantRepository.find(tenantName) != null) {
            throw new RuntimeException("租户名称已存在");
        }
        if(tenantRepository.find(tenantCode) != null) {
            throw new RuntimeException("租户编码已存在");
        }
        Map<String ,Object> param = new HashMap<>();
        param.put("permissionLevel", PermissionLevelEnum.TENANT.getValue());
        List<Permission> tenantPermission =  permissionRepository.queryList(param);
        Role adminRole = new Role(new RoleCode(RoleCode.TENANT_ADMIN), new RoleName(RoleName.TENANT_ADMIN),tenantPermission);
        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        User creator = new User(userName,account,roles);
        Tenant tenant = new Tenant(tenantCode, tenantName, creator);
        return tenant;
    }


}
