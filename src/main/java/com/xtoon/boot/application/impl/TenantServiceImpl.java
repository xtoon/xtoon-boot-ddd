package com.xtoon.boot.application.impl;

import com.xtoon.boot.application.TenantService;
import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.domain.repository.*;
import com.xtoon.boot.domain.shared.StatusEnum;
import com.xtoon.boot.domain.specification.TenantCreateSpecification;
import com.xtoon.boot.infrastructure.util.mybatis.TenantContext;
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
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerTenant(TenantName tenantName, TenantCode tenantCode, UserName userName, Account account) {
        Tenant tenant = new Tenant(tenantCode, tenantName);
        TenantCreateSpecification roleCreateSpecification = new TenantCreateSpecification(tenantRepository);
        roleCreateSpecification.isSatisfiedBy(tenant);
        // 保存租户
        TenantId tenantId = tenantRepository.store(tenant);
        TenantContext.setTenantId(tenantId.getId());
        // 保存账号
        AccountId accountId = accountRepository.store(account);
        // 保存角色
        Map<String ,Object> param = new HashMap<>();
        param.put("permissionLevel", PermissionLevelEnum.TENANT.getValue());
        List<Permission> tenantPermission =  permissionRepository.queryList(param);
        Role adminRole = new Role(new RoleCode(RoleCode.TENANT_ADMIN), new RoleName(RoleName.TENANT_ADMIN),tenantPermission);
        RoleId adminRoleId = roleRepository.store(adminRole);
        // 保存用户
        List<Role> roles = new ArrayList<>();
        adminRole = roleRepository.find(adminRoleId);
        roles.add(adminRole);
        account = accountRepository.find(accountId);
        User creator = new User(userName,account,roles);
        UserId creatorId = userRepository.store(creator);
        // 更新创建人
        creator = userRepository.find(creatorId);
        tenant = new Tenant(tenantId,tenantCode,tenantName, StatusEnum.ENABLE,creator);
        tenantRepository.store(tenant);
    }

    @Override
    public void disable(TenantId tenantId) {
        if(tenantId.isPlatformId()) {
            throw new RuntimeException("平台租户无法删除");
        }
        Tenant tenant = tenantRepository.find(tenantId);
        tenant.disable();
        tenantRepository.store(tenant);
    }

}
