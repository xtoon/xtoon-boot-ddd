package com.xtoon.boot.sys.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.sys.facade.dto.TenantDTO;
import com.xtoon.boot.sys.facade.dto.UserDTO;
import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.common.util.mybatis.Query;
import com.xtoon.boot.common.util.TenantContext;
import com.xtoon.boot.sys.application.UserApplicationService;
import com.xtoon.boot.sys.domain.model.types.*;
import com.xtoon.boot.sys.facade.UserFacadeService;
import com.xtoon.boot.sys.facade.assembler.PageAssembler;
import com.xtoon.boot.sys.facade.assembler.UserDTOAssembler;
import com.xtoon.boot.sys.facade.dto.LoginSuccessDTO;
import com.xtoon.boot.sys.domain.model.user.User;
import com.xtoon.boot.sys.domain.repository.UserRepository;
import com.xtoon.boot.sys.domain.specification.LoginByTokenSpecification;
import com.xtoon.boot.sys.infrastructure.repository.entity.SysPermissionDO;
import com.xtoon.boot.sys.infrastructure.repository.entity.SysTenantDO;
import com.xtoon.boot.sys.infrastructure.repository.entity.SysUserDO;
import com.xtoon.boot.sys.infrastructure.repository.mapper.SysPermissionMapper;
import com.xtoon.boot.sys.infrastructure.repository.mapper.SysTenantMapper;
import com.xtoon.boot.sys.infrastructure.repository.mapper.SysUserMapper;
import com.xtoon.boot.sys.facade.assembler.LoginSuccessDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用户FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Component
public class UserFacadeServiceImpl implements UserFacadeService {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysUserDO> page = sysUserMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }

    @Override
    public LoginSuccessDTO loginByAccount(String accountName, String password) {
        User user = userApplicationService.login(new Mobile(accountName), password);
        return LoginSuccessDTOAssembler.toDTO(user);
    }

    @Override
    public LoginSuccessDTO loginByMobile(String mobile) {
        User user = userApplicationService.login(new Mobile(mobile));
        return LoginSuccessDTOAssembler.toDTO(user);
    }

    @Override
    public void logout(String userId) {
        userApplicationService.logout(new UserId(userId));
    }

    @Override
    public void changePassword(String userId, String oldPasswordStr, String newPasswordStr) {
        userApplicationService.changePassword(new UserId(userId),oldPasswordStr,newPasswordStr);
    }

    @Override
    public UserDTO find(String userId) {
        return UserDTOAssembler.fromUser(userRepository.find(new UserId(userId)));
    }


    @Override
    public void save(UserDTO userDTO) {
        List<RoleId> roleIdList = new ArrayList<>();
        if(userDTO.getRoleIdList() != null) {
            userDTO.getRoleIdList().forEach(roleId -> {
                roleIdList.add(new RoleId(roleId));
            });
        }
        userApplicationService.addUser(new Mobile(userDTO.getMobile()), new Email(userDTO.getEmail()), Password.create(Password.DEFAULT),
                new UserName(userDTO.getUserName()), roleIdList,new TenantId(TenantContext.getTenantId()));
    }

    @Override
    public void update(UserDTO userDTO) {
        userApplicationService.update(UserDTOAssembler.toUser(userDTO));
    }

    @Override
    public void deleteBatch(List<String> ids) {
        List<UserId> userIds= new ArrayList<>();
        ids.forEach(id -> {
            userIds.add(new UserId(id));
        });
        userApplicationService.delete(userIds);
    }

    @Override
    public void disable(String id) {
        userApplicationService.disable(new UserId(id));
    }

    @Override
    public UserDTO queryByToken(String token) {
        Token accountToken = new Token(token,null);
        User user = userRepository.find(accountToken);
        LoginByTokenSpecification loginByTokenSpecification = new LoginByTokenSpecification();
        loginByTokenSpecification.isSatisfiedBy(user);
        UserDTO userDTO = UserDTOAssembler.fromUser(user);
        SysTenantDO tenantDO = sysTenantMapper.selectById(user.getTenantId());
        userDTO.setTenantName(tenantDO.getTenantName());
        List<SysPermissionDO> sysPermissionDOList;
        if(user.getUserId().isSysAdmin()) {
            sysPermissionDOList = sysPermissionMapper.selectList(new QueryWrapper<SysPermissionDO>().eq("status", StatusEnum.ENABLE.getValue()));
        } else {
            sysPermissionDOList = sysPermissionMapper.queryPermissionByUserId(user.getUserId().getId());
        }
        Set<String> permissionIds = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        for(SysPermissionDO sysPermissionDO : sysPermissionDOList){
            permissionIds.add(sysPermissionDO.getId());
            if(sysPermissionDO.getPermissionCodes() != null){
                permsSet.addAll(Arrays.asList(sysPermissionDO.getPermissionCodes().trim().split(",")));
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("token",token);
        List<SysUserDO> sysUserDOList =  sysUserMapper.queryUserNoTenant(params);
        List<TenantDTO> tenants = new ArrayList<>();
        for(SysUserDO sysUserDO : sysUserDOList) {
            TenantDTO tenantDTO = new TenantDTO();
            tenantDTO.setTenantId(sysUserDO.getTenantId());
            tenantDTO.setTenantCode(sysUserDO.getTenantCode());
            tenantDTO.setTenantName(sysUserDO.getTenantName());
            tenants.add(tenantDTO);
        }
        userDTO.setPermissionCodes(permsSet);
        userDTO.setPermissionIds(permissionIds);
        userDTO.setTenants(tenants);
        return userDTO;
    }

}
