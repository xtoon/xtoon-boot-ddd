package com.xtoon.boot.interfaces.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.application.TenantApplicationService;
import com.xtoon.boot.domain.model.types.*;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysTenantDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysTenantMapper;
import com.xtoon.boot.infrastructure.util.mybatis.Query;
import com.xtoon.boot.interfaces.common.Page;
import com.xtoon.boot.interfaces.facade.SysTenantServiceFacade;
import com.xtoon.boot.interfaces.facade.assembler.PageAssembler;
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
    private TenantApplicationService tenantApplicationService;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysTenantDO> page = sysTenantMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }

    @Override
    public void registerTenant(String tenantName, String tenantCode, String userName,String mobile, String password) {
        tenantApplicationService.registerTenant(new TenantName(tenantName), new TenantCode(tenantCode),new Mobile(mobile), Password.create(password), new UserName(userName));
    }

    @Override
    public void disable(String id) {
        tenantApplicationService.disable(new TenantId(id));
    }
}
