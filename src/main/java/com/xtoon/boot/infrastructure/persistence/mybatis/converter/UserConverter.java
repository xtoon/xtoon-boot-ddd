package com.xtoon.boot.infrastructure.persistence.mybatis.converter;

import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantCode;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.system.types.TenantName;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.UserId;
import com.xtoon.boot.domain.model.user.types.UserName;
import com.xtoon.boot.domain.shared.StatusEnum;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserDO;

import java.util.List;

/**
 * 用户Converter
 *
 * @author haoxin
 * @date 2021-02-10
 **/
public class UserConverter {

    public static User toUser(SysUserDO sysUserDO, Account account, List<Role> roles) {
        if(sysUserDO == null) {
            return null;
        }
        Tenant tenant = null;
        if(sysUserDO.getTenantId() != null) {
            tenant = new Tenant(new TenantId(sysUserDO.getTenantId()),new TenantCode(sysUserDO.getTenantCode()),new TenantName(sysUserDO.getTenantName()),StatusEnum.ENABLE,null);
        }
        User user = new User(new UserId(sysUserDO.getId()), new UserName(sysUserDO.getUserName()), StatusEnum.getStatusEnum(sysUserDO.getStatus()),tenant,account,roles);
        return user;
    }

    public static SysUserDO fromUser(User user) {
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setId(user.getUserId()==null?null:user.getUserId().getId());
        sysUserDO.setUserName(user.getUserName()==null?null:user.getUserName().getName());
        sysUserDO.setStatus(user.getStatus()==null?null:user.getStatus().getValue());
        sysUserDO.setAccountId(user.getAccount()==null?null:user.getAccount().getAccountId().getId());
        return sysUserDO;
    }
}
