package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.AccountId;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.model.user.types.Token;
import com.xtoon.boot.domain.model.user.types.UserId;
import com.xtoon.boot.domain.repository.AccountRepository;
import com.xtoon.boot.domain.repository.RoleRepository;
import com.xtoon.boot.domain.repository.UserRepository;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.UserConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysRoleDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserRoleDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysRoleMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysUserMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户-Repository实现类
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@Repository
public class UserRepositoryImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements UserRepository, IService<SysUserDO> {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User find(UserId userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId",userId.getId());
        SysUserDO sysUserDO = baseMapper.queryUser(params);
        if(sysUserDO == null) {
            return null;
        }
        User user = UserConverter.toUser(sysUserDO);
        addUserAccount(user, sysUserDO.getAccountId());
        addUserRoles(user);
        return user;
    }

    @Override
    public User find(Token token) {
        Map<String, Object> params = new HashMap<>();
        params.put("token",token.getToken());
        SysUserDO sysUserDO = baseMapper.queryUser(params);
        if(sysUserDO == null) {
            return null;
        }
        User user = UserConverter.toUser(sysUserDO);
        addUserAccount(user, sysUserDO.getAccountId());
        addUserRoles(user);
        return user;
    }

    @Override
    public void store(User user) {
        SysUserDO sysUserDO = UserConverter.fromUser(user);
        this.saveOrUpdate(sysUserDO);
        String userId = sysUserDO.getId();
        //先删除用户与角色关系
        List<String> userIds = new ArrayList<>();
        userIds.add(userId);
        sysUserRoleMapper.deleteByUserIds(userIds);
        List<String> roleIds = user.getUserRoleIds();
        if(roleIds !=null && !roleIds.isEmpty()) {
            //保存角色与菜单关系
            for(String roleId : roleIds){
                SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
                sysUserRoleDO.setUserId(userId);
                sysUserRoleDO.setRoleId(roleId);
                sysUserRoleMapper.insert(sysUserRoleDO);
            }
        }
        user.setUserId(new UserId(sysUserDO.getId()));
    }

    @Override
    public void update(User user) {
        SysUserDO sysUserDO = UserConverter.fromUser(user);
        this.saveOrUpdate(sysUserDO);
        user.setUserId(new UserId(sysUserDO.getId()));
    }

    @Override
    public void delete(List<UserId> userIds) {
        List<String> ids = new ArrayList<>();
        userIds.forEach(userId -> {
            ids.add(userId.getId());
        });
        this.removeByIds(ids);
        sysUserRoleMapper.deleteByUserIds(ids);
    }

    /**
     * 添加账号
     *
     * @param user
     * @param accountId
     */
    private void addUserAccount(User user,String accountId) {
        Account account = accountRepository.find(new AccountId(accountId));
        user.setAccount(account);
    }

    /**
     * 添加角色
     *
     * @param user
     */
    private void addUserRoles(User user) {
        // 如果是超级管理员
        List<SysRoleDO> sysRoleDOList = sysRoleMapper.queryUserRole(user.getUserId().getId());
        if(sysRoleDOList != null && !sysRoleDOList.isEmpty()) {
            List<Role> roleList = new ArrayList<>();
            for (SysRoleDO sysRoleDO : sysRoleDOList) {
                roleList.add(roleRepository.find(new RoleId(sysRoleDO.getId())));
            }
            user.setRoles(roleList);
        }
    }

}
