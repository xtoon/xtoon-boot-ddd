package com.xtoon.boot.interfaces.sys.facade.assembler;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.PermissionId;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.model.user.types.RoleName;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysRoleDO;
import com.xtoon.boot.interfaces.sys.facade.dto.RoleDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色Assembler
 *
 * @author haoxin
 * @date 2021-02-18
 **/
public class RoleDTOAssembler {

    public static RoleDTO fromRole(final Role role) {
        final RoleDTO dto = new RoleDTO();
        dto.setId(role.getRoleId()==null?null:role.getRoleId().getId());
        dto.setRoleCode(role.getRoleCode()==null?null:role.getRoleCode().getCode());
        dto.setRoleName(role.getRoleName()==null?null:role.getRoleName().getName());
        dto.setRemarks(role.getRemarks());
        dto.setPermissionIdList(role.getPermissionIds());
        dto.setStatus(role.getStatus()==null?null:role.getStatus().getValue());
        return dto;
    }

    public static Role toRole(final RoleDTO roleDTO) {
        RoleId roleId = null;
        if(roleDTO.getId() != null) {
            roleId = new RoleId(roleDTO.getId());
        }
        RoleCode roleCode = null;
        if(roleDTO.getRoleCode() != null) {
            roleCode = new RoleCode(roleDTO.getRoleCode());
        }
        RoleName roleName = null;
        if(roleDTO.getRoleName() != null) {
            roleName = new RoleName(roleDTO.getRoleName());
        }
        List<Permission> permissionList = null;
        if(roleDTO.getPermissionIdList() != null) {
            permissionList = new ArrayList<>();
            for(String permissionId:roleDTO.getPermissionIdList()) {
                permissionList.add(new Permission(new PermissionId(permissionId)));
            }
        }
        Role Role = new Role(roleId,roleCode,roleName,roleDTO.getRemarks(),null,permissionList);
        return Role;
    }

    public static RoleDTO getRoleDTO(final SysRoleDO sysRoleDO) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(sysRoleDO, roleDTO);
        return roleDTO;
    }

    public static List<RoleDTO> getRoleDTOList(final List<SysRoleDO> roles) {
        if(roles == null) {
            return null;
        }
        final List<RoleDTO> List = new ArrayList<>();
        for(SysRoleDO sysRoleDO : roles) {
            List.add(getRoleDTO(sysRoleDO));
        }
        return List;
    }
}
