package com.xtoon.boot.sys.domain.service;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.domain.model.user.UserFactory;
import com.xtoon.boot.sys.domain.model.permission.Permission;
import com.xtoon.boot.sys.domain.model.permission.PermissionId;
import com.xtoon.boot.sys.domain.model.permission.PermissionLevelEnum;
import com.xtoon.boot.sys.domain.model.role.Role;
import com.xtoon.boot.sys.domain.model.role.RoleCode;
import com.xtoon.boot.sys.domain.model.role.RoleId;
import com.xtoon.boot.sys.domain.model.role.RoleName;
import com.xtoon.boot.sys.domain.model.tenant.Tenant;
import com.xtoon.boot.sys.domain.model.tenant.TenantCode;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import com.xtoon.boot.sys.domain.model.tenant.TenantName;
import com.xtoon.boot.sys.domain.model.user.*;
import com.xtoon.boot.sys.domain.model.permission.PermissionRepository;
import com.xtoon.boot.sys.domain.model.role.RoleRepository;
import com.xtoon.boot.sys.domain.model.tenant.TenantRepository;
import com.xtoon.boot.sys.domain.model.user.UserRepository;
import com.xtoon.boot.sys.domain.specification.TenantCreateSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租户注册服务
 *
 * @author haoxin
 * @date 2021-05-10
 **/
public class TenantRegisterService {

    private TenantRepository tenantRepository;

    private RoleRepository roleRepository;

    private PermissionRepository permissionRepository;

    private UserRepository userRepository;

    private UserFactory userFactory;

    public TenantRegisterService(TenantRepository tenantRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, UserFactory userFactory) {
        this.tenantRepository = tenantRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public void registerTenant(TenantName tenantName, TenantCode tenantCode, Mobile mobile, Password password, UserName userName) {
        Tenant tenant = new Tenant(tenantCode, tenantName);
        TenantCreateSpecification roleCreateSpecification = new TenantCreateSpecification(tenantRepository);
        roleCreateSpecification.isSatisfiedBy(tenant);
        // 保存租户
        TenantId tenantId = tenantRepository.store(tenant);
        // 保存角色
        Map<String ,Object> param = new HashMap<>();
        param.put("permissionLevel", PermissionLevelEnum.TENANT.getValue());
        List<Permission> tenantPermission =  permissionRepository.queryList(param);
        List<PermissionId> tenantPermissionId = new ArrayList<>();
        for(Permission permission:tenantPermission) {
            tenantPermissionId.add(permission.getPermissionId());
        }
        Role adminRole = new Role(new RoleCode(RoleCode.TENANT_ADMIN), new RoleName(RoleName.TENANT_ADMIN),tenantPermissionId);
        RoleId adminRoleId = roleRepository.store(adminRole);
        // 保存用户
        List<RoleId> roleIdList = new ArrayList<>();
        roleIdList.add(adminRoleId);
        User user = userFactory.createUser(mobile, null, password, userName, roleIdList, new TenantId(tenantId.getId()));
        UserId userId = userRepository.store(user);
        tenant = new Tenant(tenantId,tenantCode,tenantName, StatusEnum.ENABLE,userId);
        tenantRepository.store(tenant);
    }
}
