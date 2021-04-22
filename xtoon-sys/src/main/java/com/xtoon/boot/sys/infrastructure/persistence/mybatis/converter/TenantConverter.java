package com.xtoon.boot.sys.infrastructure.persistence.mybatis.converter;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.domain.model.Tenant;
import com.xtoon.boot.sys.domain.model.types.TenantCode;
import com.xtoon.boot.sys.domain.model.types.TenantId;
import com.xtoon.boot.sys.domain.model.types.TenantName;
import com.xtoon.boot.sys.domain.model.types.UserId;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.entity.SysTenantDO;

/**
 * 租户Converter
 *
 * @author haoxin
 * @date 2021-02-15
 **/
public class TenantConverter {

    public static Tenant toTenant(SysTenantDO sysTenantDO) {
        if(sysTenantDO == null) {
            return null;
        }
        TenantId tenantId = null;
        if(sysTenantDO.getId() != null) {
            tenantId = new TenantId(sysTenantDO.getId());
        }
        UserId creatorId = null;
        if(sysTenantDO.getCreatorId() != null) {
            creatorId = new UserId(sysTenantDO.getCreatorId());
        }
        Tenant tenant = new Tenant(tenantId, new TenantCode(sysTenantDO.getTenantCode()), new TenantName(sysTenantDO.getTenantName()), StatusEnum.getStatusEnum(sysTenantDO.getStatus()),creatorId);
        return tenant;
    }

    public static SysTenantDO getSysTenantDO(Tenant tenant) {
        if(tenant == null) {
            throw new RuntimeException("租户不存在");
        }
        SysTenantDO sysTenantDO = new SysTenantDO();
        sysTenantDO.setId(tenant.getTenantId()==null?null:tenant.getTenantId().getId());
        sysTenantDO.setTenantCode(tenant.getTenantCode()==null?null:tenant.getTenantCode().getCode());
        sysTenantDO.setTenantName(tenant.getTenantName()==null?null:tenant.getTenantName().getName());
        sysTenantDO.setStatus(tenant.getStatus()==null?null:tenant.getStatus().getValue());
        sysTenantDO.setCreatorId(tenant.getCreatorId() == null ?null:tenant.getCreatorId().getId());
        return sysTenantDO;
    }
}
