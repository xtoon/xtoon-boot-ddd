package com.xtoon.boot.sys.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.common.util.PageAssembler;
import com.xtoon.boot.common.util.mybatis.Query;
import com.xtoon.boot.sys.application.UserQueryService;
import com.xtoon.boot.sys.application.assembler.UserDTOAssembler;
import com.xtoon.boot.sys.application.dto.TenantDTO;
import com.xtoon.boot.sys.application.dto.UserDTO;
import com.xtoon.boot.sys.domain.model.user.Token;
import com.xtoon.boot.sys.domain.model.user.UserId;
import com.xtoon.boot.sys.domain.model.user.User;
import com.xtoon.boot.sys.domain.model.user.UserRepository;
import com.xtoon.boot.sys.domain.specification.LoginByTokenSpecification;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysPermissionDO;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysTenantDO;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysUserDO;
import com.xtoon.boot.sys.infrastructure.persistence.mapper.SysPermissionMapper;
import com.xtoon.boot.sys.infrastructure.persistence.mapper.SysTenantMapper;
import com.xtoon.boot.sys.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysUserDO> page = sysUserMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }

    @Override
    public UserDTO find(String userId) {
        return UserDTOAssembler.fromUser(userRepository.find(new UserId(userId)));
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
