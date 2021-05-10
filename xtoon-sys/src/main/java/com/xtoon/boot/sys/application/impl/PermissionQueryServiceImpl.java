package com.xtoon.boot.sys.application.impl;

import com.xtoon.boot.common.util.TenantContext;
import com.xtoon.boot.sys.application.PermissionQueryService;
import com.xtoon.boot.sys.application.assembler.PermissionDTOAssembler;
import com.xtoon.boot.sys.application.dto.PermissionDTO;
import com.xtoon.boot.sys.application.dto.UserDTO;
import com.xtoon.boot.sys.domain.model.permission.Permission;
import com.xtoon.boot.sys.domain.model.permission.PermissionId;
import com.xtoon.boot.sys.domain.model.permission.PermissionTypeEnum;
import com.xtoon.boot.sys.domain.model.role.RoleCode;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import com.xtoon.boot.sys.domain.model.permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 权限查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class PermissionQueryServiceImpl implements PermissionQueryService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> listAllPermission() {
        List<Permission> permissionList;
        if(TenantId.PLATFORM_TENANT.equals(TenantContext.getTenantId())) {
            permissionList = permissionRepository.queryList(new HashMap<>());
        } else {
            permissionList = permissionRepository.queryList(new RoleCode(RoleCode.TENANT_ADMIN));
        }
        return PermissionDTOAssembler.getPermissionList(permissionList);
    }

    @Override
    public List<PermissionDTO> listAllMenu() {
        List<Permission> permissionList;
        if(TenantId.PLATFORM_TENANT.equals(TenantContext.getTenantId())) {
            permissionList = permissionRepository.queryList(new HashMap<>());
        } else {
            permissionList = permissionRepository.queryList(new RoleCode(RoleCode.TENANT_ADMIN));
        }
        return PermissionDTOAssembler.getMenuList(permissionList);
    }

    @Override
    public PermissionDTO getById(String id) {
        Permission permission = permissionRepository.find(new PermissionId(id));
        return PermissionDTOAssembler.fromPermission(permission);
    }

    @Override
    public List<PermissionDTO> getUserMenuTree(UserDTO user) {
        Set<String> menuIdList = user.getPermissionIds();
        if(menuIdList == null) {
            return null;
        }
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<PermissionDTO> getAllMenuList(Set<String> menuIdList){
        //查询根菜单列表
        List<PermissionDTO> menuList = queryListParentId(Permission.ROOT_ID, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 通过父级id获取权限
     *
     * @param parentId
     * @param menuIdList
     * @return
     */
    private List<PermissionDTO> queryListParentId(String parentId, Set<String> menuIdList) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId",parentId);
        List<PermissionDTO> menuList = PermissionDTOAssembler.getPermissionList(permissionRepository.queryList(params));
        if(menuIdList == null){
            return menuList;
        }
        List<PermissionDTO> userMenuList = new ArrayList<>();
        for(PermissionDTO menu : menuList){
            if(menuIdList.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 递归
     */
    private List<PermissionDTO> getMenuTreeList(List<PermissionDTO> menuList, Set<String> menuIdList){
        List<PermissionDTO> subMenuList = new ArrayList<>();
        for(PermissionDTO entity : menuList){
            //目录
            if(PermissionTypeEnum.CATALOG.getValue().equals(entity.getPermissionType())){
                entity.setSubList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}
