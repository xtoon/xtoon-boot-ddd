package com.xtoon.boot.infrastructure.persistence.mybatis.converter;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.model.user.types.RoleName;
import com.xtoon.boot.domain.shared.StatusEnum;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysPermissionDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysRoleDO;
import com.xtoon.boot.infrastructure.util.exception.XTException;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色Converter
 *
 * @author haoxin
 * @date 2021-02-19
 **/
public class RoleConverter {

    public static Role toRole(SysRoleDO sysRoleDO, List<SysPermissionDO> sysPermissionDOList) {
        if(sysRoleDO == null) {
            throw new XTException("未找到角色");
        }
        List<Permission> permissions = null;
        if(sysPermissionDOList != null && !sysPermissionDOList.isEmpty()) {
            permissions = new ArrayList<>();
            for(SysPermissionDO sysPermissionDO : sysPermissionDOList) {
                permissions.add(PermissionConverter.toPermission(sysPermissionDO));
            }
        }
        Role role = new Role(new RoleId(sysRoleDO.getId()),new RoleCode(sysRoleDO.getRoleCode()),new RoleName(sysRoleDO.getRoleName()),
                sysRoleDO.getRemarks(), StatusEnum.getStatusEnum(sysRoleDO.getStatus()),permissions);
        return role;
    }

    public static SysRoleDO fromRole(Role role) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setId(role.getRoleId() == null?null:role.getRoleId().getId());
        sysRoleDO.setRoleCode(role.getRoleCode()==null?null:role.getRoleCode().getCode());
        sysRoleDO.setRoleName(role.getRoleName()==null?null:role.getRoleName().getName());
        sysRoleDO.setRemarks(role.getRemarks());
        sysRoleDO.setStatus(role.getStatus()==null?null:role.getStatus().getValue());
        return sysRoleDO;
    }

}
