package com.xtoon.boot.sys.application.impl;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.application.TenantApplicationService;
import com.xtoon.boot.sys.domain.factory.UserFactory;
import com.xtoon.boot.sys.domain.model.Permission;
import com.xtoon.boot.sys.domain.model.Role;
import com.xtoon.boot.sys.domain.model.Tenant;
import com.xtoon.boot.sys.domain.model.types.*;
import com.xtoon.boot.sys.domain.model.user.User;
import com.xtoon.boot.sys.domain.repository.PermissionRepository;
import com.xtoon.boot.sys.domain.repository.RoleRepository;
import com.xtoon.boot.sys.domain.repository.TenantRepository;
import com.xtoon.boot.sys.domain.repository.UserRepository;
import com.xtoon.boot.sys.domain.service.TenantDisableService;
import com.xtoon.boot.sys.domain.specification.TenantCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租户ServiceImpl
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Service
public class TenantApplicationServiceImpl implements TenantApplicationService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
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

    @Override
    public void disable(TenantId tenantId) {
        if(tenantId.isPlatformId()) {
            throw new RuntimeException("平台租户无法删除");
        }
        Tenant tenant = tenantRepository.find(tenantId);
        User user = userRepository.find(tenant.getCreatorId());
        TenantDisableService tenantDisableService = new TenantDisableService();
        tenantDisableService.disable(tenant,user);
        tenantRepository.store(tenant);
        userRepository.store(user);
    }

}
