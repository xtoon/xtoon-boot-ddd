package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtoon.boot.domain.model.Role;
import com.xtoon.boot.domain.model.types.PermissionId;
import com.xtoon.boot.domain.model.types.RoleCode;
import com.xtoon.boot.domain.model.types.RoleId;
import com.xtoon.boot.domain.model.types.RoleName;
import com.xtoon.boot.domain.repository.RoleRepository;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.RoleConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysPermissionDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysRoleDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysRolePermissionDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysPermissionMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysRoleMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysRolePermissionMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色-Repository实现类
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@Repository
public class RoleRepositoryImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements RoleRepository, IService<SysRoleDO> {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Role find(RoleId roleId) {
        SysRoleDO sysRoleDO = this.getById(roleId.getId());
        if(sysRoleDO == null) {
            return null;
        }
        Role role = RoleConverter.toRole(sysRoleDO, getRolePermission(sysRoleDO.getRoleCode(), sysRoleDO.getId()));
        return role;
    }

    @Override
    public Role find(RoleName roleName) {
        SysRoleDO sysRoleDO = this.getOne(new QueryWrapper<SysRoleDO>().eq("role_name", roleName.getName()));
        if(sysRoleDO == null) {
            return null;
        }
        Role role = RoleConverter.toRole(sysRoleDO, getRolePermission(sysRoleDO.getRoleCode(), sysRoleDO.getId()));
        return role;
    }

    @Override
    public Role find(RoleCode roleCode) {
        SysRoleDO sysRoleDO = this.getOne(new QueryWrapper<SysRoleDO>().eq("role_code", roleCode.getCode()));
        if(sysRoleDO == null) {
            return null;
        }
        Role role = RoleConverter.toRole(sysRoleDO, getRolePermission(sysRoleDO.getRoleCode(), sysRoleDO.getId()));
        return role;
    }

    private List<SysPermissionDO> getRolePermission(String roleCode, String roleId) {
        List<SysPermissionDO> sysPermissionDOList;
        sysPermissionDOList = sysPermissionMapper.queryPermissionByRoleId(roleId);
        return sysPermissionDOList;
    }

    @Override
    public RoleId store(Role role) {
        SysRoleDO sysRoleDO = RoleConverter.fromRole(role);
        this.saveOrUpdate(sysRoleDO);
        String roleId = sysRoleDO.getId();
        //先删除角色与菜单关系
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        sysRolePermissionMapper.deleteByRoleIds(roleIds);
        List<PermissionId> permissionIds = role.getPermissionIds();
        if(permissionIds != null && !permissionIds.isEmpty()) {
            //保存角色与菜单关系
            for(PermissionId permissionId : permissionIds){
                SysRolePermissionDO sysRolePermissionDO = new SysRolePermissionDO();
                sysRolePermissionDO.setPermissionId(permissionId.getId());
                sysRolePermissionDO.setRoleId(roleId);
                sysRolePermissionMapper.insert(sysRolePermissionDO);
            }
        }
        return new RoleId(sysRoleDO.getId());
    }

    @Override
    public void remove(List<RoleId> roleIds) {
        List<String> ids = new ArrayList<>();
        roleIds.forEach(roleId -> {
            ids.add(roleId.getId());
        });
        //删除角色
        this.removeByIds(ids);
        //删除角色与菜单关联
        sysRolePermissionMapper.deleteByRoleIds(ids);
        //删除角色与用户关联
        sysUserRoleMapper.deleteByRoleIds(ids);
    }
}
