package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtoon.boot.domain.model.Tenant;
import com.xtoon.boot.domain.model.types.TenantCode;
import com.xtoon.boot.domain.model.types.TenantId;
import com.xtoon.boot.domain.model.types.TenantName;
import com.xtoon.boot.domain.repository.TenantRepository;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.TenantConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysTenantDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysTenantMapper;
import com.xtoon.boot.infrastructure.util.mybatis.TenantContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户-Repository实现类
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Repository
public class TenantRepositoryImpl extends ServiceImpl<SysTenantMapper, SysTenantDO> implements TenantRepository, IService<SysTenantDO> {

    @Override
    public Tenant find(TenantId tenantId) {
        SysTenantDO sysTenantDO = this.getById(tenantId.getId());
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        return tenant;
    }

    @Override
    public Tenant find(TenantName tenantName) {
        SysTenantDO sysTenantDO = this.getOne(new QueryWrapper<SysTenantDO>().eq("tenant_name", tenantName.getName()));
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        return tenant;
    }

    @Override
    public Tenant find(TenantCode tenantCode) {
        SysTenantDO sysTenantDO = this.getOne(new QueryWrapper<SysTenantDO>().eq("tenant_code", tenantCode.getCode()));
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        return tenant;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TenantId store(Tenant tenant) {
        SysTenantDO sysTenantDO = TenantConverter.getSysTenantDO(tenant);
        this.saveOrUpdate(sysTenantDO);
        if(TenantContext.getTenantId() == null) {
            TenantContext.setTenantId(sysTenantDO.getId());
        }
        return new TenantId(sysTenantDO.getId());
    }
}
