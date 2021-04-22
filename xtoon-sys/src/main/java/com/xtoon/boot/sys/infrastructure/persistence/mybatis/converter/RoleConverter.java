package com.xtoon.boot.sys.infrastructure.persistence.mybatis.converter;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.common.util.exception.XTException;
import com.xtoon.boot.sys.domain.model.Role;
import com.xtoon.boot.sys.domain.model.types.PermissionId;
import com.xtoon.boot.sys.domain.model.types.RoleCode;
import com.xtoon.boot.sys.domain.model.types.RoleId;
import com.xtoon.boot.sys.domain.model.types.RoleName;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.entity.SysPermissionDO;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.entity.SysRoleDO;

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
        List<PermissionId> permissionIds = null;
        if(sysPermissionDOList != null && !sysPermissionDOList.isEmpty()) {
            permissionIds = new ArrayList<>();
            for(SysPermissionDO sysPermissionDO : sysPermissionDOList) {
                permissionIds.add(new PermissionId(sysPermissionDO.getId()));
            }
        }
        Role role = new Role(new RoleId(sysRoleDO.getId()),new RoleCode(sysRoleDO.getRoleCode()),new RoleName(sysRoleDO.getRoleName()),
                sysRoleDO.getRemarks(), StatusEnum.getStatusEnum(sysRoleDO.getStatus()),permissionIds);
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
