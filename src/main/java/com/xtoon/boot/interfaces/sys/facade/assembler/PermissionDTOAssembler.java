package com.xtoon.boot.interfaces.sys.facade.assembler;

import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.interfaces.sys.facade.dto.PermissionDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Assembler class for the Permission.
 *
 * @author haoxin
 * @date 2021-02-17
 **/
public class PermissionDTOAssembler {

    public static PermissionDTO fromPermission(final Permission permission) {
        final PermissionDTO dto = new PermissionDTO();
        dto.setId(permission.getPermissionId()==null?null:permission.getPermissionId().getId());
        dto.setPermissionName(permission.getPermissionName()==null?null:permission.getPermissionName().getName());
        dto.setPermissionType(permission.getPermissionType()==null?null:permission.getPermissionType().getValue());
        dto.setMenuIcon(permission.getMenuIcon());
        dto.setMenuUrl(permission.getMenuUrl()==null?null:permission.getMenuUrl().getUrl());
        dto.setOrderNum(permission.getOrderNum());
        dto.setParentId(permission.getParent()==null?null:permission.getParent().getPermissionId().getId());
        dto.setParentName(permission.getParent()==null?null:permission.getParent().getPermissionName().getName());
        dto.setPermissionCodes(permission.getPermissionCodes()==null?null:permission.getPermissionCodes().getCodesString());
        dto.setPermissionLevel(permission.getPermissionLevel()==null?null:permission.getPermissionLevel().getValue());
        return dto;
    }

    public static Permission toPermission(final PermissionDTO permissionDTO) {
        PermissionId permissionId = null;
        if(permissionDTO.getId() != null) {
            permissionId = new PermissionId(permissionDTO.getId());
        }
        PermissionName permissionName = null;
        if(permissionDTO.getPermissionName() != null) {
            permissionName = new PermissionName(permissionDTO.getPermissionName());
        }
        PermissionTypeEnum permissionType = null;
        if(permissionDTO.getPermissionType() != null) {
            permissionType = PermissionTypeEnum.getMenuTypeEnum(permissionDTO.getPermissionType());
        }
        PermissionLevelEnum permissionLevel = null;
        if(permissionDTO.getPermissionLevel() !=null) {
            permissionLevel = PermissionLevelEnum.getMenuLevelEnum(permissionDTO.getPermissionLevel());
        }
        PermissionCodes permissionCodes = null;
        if(permissionDTO.getPermissionCodes() != null) {
            Set<String> permsSet = new HashSet<>();
            permsSet.addAll(Arrays.asList(permissionDTO.getPermissionCodes().trim().split(",")));
            permissionCodes = new PermissionCodes(permsSet);
        }
        MenuUrl menuUrl = null;
        if(!StringUtils.isEmpty(permissionDTO.getMenuUrl())) {
            menuUrl = new MenuUrl(permissionDTO.getMenuUrl());
        }
        Permission permission = new Permission(permissionId,permissionName,permissionType,permissionLevel,permissionDTO.getMenuIcon(),permissionCodes,permissionDTO.getOrderNum(),
                menuUrl,null, null,null);
        return permission;
    }


    public static List<PermissionDTO> getPermissionList(final List<Permission> permissionList) {
        if(permissionList == null) {
            return null;
        }
        final List<PermissionDTO> List = new ArrayList<>();
        for(Permission permission : permissionList) {
            List.add(fromPermission(permission));
        }
        return List;
    }

    public static List<PermissionDTO> getMenuList(final List<Permission> permissionList) {
        if(permissionList == null) {
            return null;
        }
        final List<PermissionDTO> List = new ArrayList<>();
        for(Permission permission : permissionList) {
            if(permission.isMenu()) {
                List.add(fromPermission(permission));
            }
        }
        return List;
    }
}
