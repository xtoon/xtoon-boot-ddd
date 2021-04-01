package com.xtoon.boot.interfaces.facade.impl;

import com.xtoon.boot.application.PermissionApplicationService;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.user.Permission;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.PermissionId;
import com.xtoon.boot.domain.model.user.types.PermissionTypeEnum;
import com.xtoon.boot.domain.model.user.types.RoleCode;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.infrastructure.util.mybatis.TenantContext;
import com.xtoon.boot.interfaces.facade.SysPermissionServiceFacade;
import com.xtoon.boot.interfaces.facade.assembler.PermissionDTOAssembler;
import com.xtoon.boot.interfaces.facade.dto.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-17
 **/
@Component
public class SysPermissionServiceFacadeImpl implements SysPermissionServiceFacade {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionApplicationService permissionApplicationService;

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
    public void saveOrUpdate(PermissionDTO permissionDTO) {
        Permission parent = permissionRepository.find(new PermissionId(permissionDTO.getParentId()));
        Permission permission = PermissionDTOAssembler.toPermission(permissionDTO,parent);
        permissionApplicationService.saveOrUpdate(permission);
    }

    @Override
    public void delete(String id) {
        permissionApplicationService.delete(new PermissionId(id));
    }

    @Override
    public void disable(String id) {
        permissionApplicationService.disable(new PermissionId(id));
    }

    @Override
    public List<PermissionDTO> getUserMenuTree(User user) {
        List<String> menuIdList = user.getUserPermissionIds();
        if(menuIdList == null) {
            return null;
        }
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<PermissionDTO> getAllMenuList(List<String> menuIdList){
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
    private List<PermissionDTO> queryListParentId(String parentId, List<String> menuIdList) {
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
    private List<PermissionDTO> getMenuTreeList(List<PermissionDTO> menuList, List<String> menuIdList){
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
