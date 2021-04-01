package com.xtoon.boot.interfaces.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.application.TenantApplicationService;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.types.Mobile;
import com.xtoon.boot.domain.model.user.types.UserName;
import com.xtoon.boot.domain.shared.Page;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.PageConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysTenantDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysTenantMapper;
import com.xtoon.boot.infrastructure.util.mybatis.Query;
import com.xtoon.boot.interfaces.facade.SysTenantServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 租户FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Component
public class SysTenantServiceFacadeImpl implements SysTenantServiceFacade {

    @Autowired
    private TenantApplicationService tenantApplicatioService;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysTenantDO> page = sysTenantMapper.queryPage(new Query().getPage(params),params);
        return PageConverter.toPage(page);
    }

    @Override
    public void registerTenant(String tenantName, String tenantCode, String userName, Account account) {
        tenantApplicatioService.registerTenant(new TenantName(tenantName), new TenantCode(tenantCode), new UserName(userName), account);
    }

    @Override
    public void registerTenant(String tenantName, String tenantCode, String userName,String mobile, String password) {
        Account account = new Account(new Mobile(mobile), password);
        tenantApplicatioService.registerTenant(new TenantName(tenantName), new TenantCode(tenantCode), new UserName(userName), account);
    }

    @Override
    public void disable(String id) {
        tenantApplicatioService.disable(new TenantId(id));
    }
}
