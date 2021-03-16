package com.xtoon.boot.application.impl;

import com.xtoon.boot.application.TenantService;
import com.xtoon.boot.domain.factory.TenantFactory;
import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.UserName;
import com.xtoon.boot.domain.repository.AccountRepository;
import com.xtoon.boot.domain.repository.RoleRepository;
import com.xtoon.boot.domain.repository.TenantRepository;
import com.xtoon.boot.domain.repository.UserRepository;
import com.xtoon.boot.infrastructure.util.mybatis.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户ServiceImpl
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantFactory tenantFactory;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerTenant(TenantName tenantName, TenantCode tenantCode, UserName userName, Account account) {
        Tenant tenant = tenantFactory.createTenant(tenantName, tenantCode, userName, account);
        tenantRepository.store(tenant);
        TenantContext.setTenantId(tenant.getTenantId().getId());
        User user = tenant.getCreator();
        account = user.getAccount();
        accountRepository.store(account);
        Role role = user.getRoles().get(0);
        roleRepository.store(role);
        userRepository.store(user);
        tenant.getCreator().setUserId(user.getUserId());
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
