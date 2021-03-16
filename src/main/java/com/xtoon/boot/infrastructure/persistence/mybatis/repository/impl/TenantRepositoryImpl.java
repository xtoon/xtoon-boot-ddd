package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.repository.TenantRepository;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.TenantConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.UserConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysTenantDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysTenantMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 租户-Repository实现类
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Repository
public class TenantRepositoryImpl extends ServiceImpl<SysTenantMapper, SysTenantDO> implements TenantRepository, IService<SysTenantDO> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Tenant find(TenantId tenantId) {
        SysTenantDO sysTenantDO = this.getById(tenantId.getId());
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        setCreator(tenant,sysTenantDO.getCreatorId());
        return tenant;
    }

    @Override
    public Tenant find(TenantName tenantName) {
        SysTenantDO sysTenantDO = this.getOne(new QueryWrapper<SysTenantDO>().eq("tenant_name", tenantName.getName()));
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        setCreator(tenant,sysTenantDO.getCreatorId());
        return tenant;
    }

    @Override
    public Tenant find(TenantCode tenantCode) {
        SysTenantDO sysTenantDO = this.getOne(new QueryWrapper<SysTenantDO>().eq("tenant_code", tenantCode.getCode()));
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        setCreator(tenant,sysTenantDO.getCreatorId());
        return tenant;
    }

    /**
     * 设置创建者
     *
     * @param tenant
     * @param creatorId
     */
    private void setCreator(Tenant tenant,String creatorId) {
        if(!StringUtils.isEmpty(creatorId)) {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", creatorId);
            SysUserDO sysUserDO = sysUserMapper.queryUser(params);
            if(sysUserDO != null) {
                tenant.setCreator(UserConverter.toUser(sysUserDO));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void store(Tenant tenant) {
        SysTenantDO sysTenantDO = TenantConverter.getSysTenantDO(tenant);
        this.saveOrUpdate(sysTenantDO);
        tenant.setTenantId(new TenantId(sysTenantDO.getId()));
    }
}
