package com.xtoon.boot.interfaces.sys.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.application.AccountService;
import com.xtoon.boot.application.UserService;
import com.xtoon.boot.domain.model.system.Tenant;
import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.domain.repository.TenantRepository;
import com.xtoon.boot.domain.repository.UserRepository;
import com.xtoon.boot.domain.shared.Page;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.PageConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysUserMapper;
import com.xtoon.boot.infrastructure.util.mybatis.Query;
import com.xtoon.boot.infrastructure.util.mybatis.TenantContext;
import com.xtoon.boot.interfaces.sys.facade.SysUserServiceFacade;
import com.xtoon.boot.interfaces.sys.facade.assembler.UserDTOAssembler;
import com.xtoon.boot.interfaces.sys.facade.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Component
public class SysUserServiceFacadeImpl implements SysUserServiceFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysUserDO> page = sysUserMapper.queryPage(new Query().getPage(params),params);
        return PageConverter.toPage(page);
    }

    @Override
    public void changePassword(Account account, String oldPasswordStr, String newPasswordStr) {
        accountService.changePassword(account,oldPasswordStr,newPasswordStr);
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
        userService.addUser(new Mobile(userDTO.getMobile()), new Email(userDTO.getEmail()), Password.create(Password.DEFAULT),
                new UserName(userDTO.getUserName()), roleIdList );
    }

    @Override
    public void update(UserDTO userDTO) {
        userService.update(UserDTOAssembler.toUser(userDTO));
    }

    @Override
    public void deleteBatch(List<String> ids) {
        List<UserId> userIds= new ArrayList<>();
        ids.forEach(id -> {
            userIds.add(new UserId(id));
        });
        userService.delete(userIds);
    }

    @Override
    public void disable(String id) {
        userService.disable(new UserId(id));
    }

    @Override
    public void registerUser(String tenantId, String roleId, String mobile, String userName) {
        Tenant tenant = tenantRepository.find(new TenantId(tenantId));
        if(tenant == null) {
            throw new RuntimeException("租户不存在");
        }
        TenantContext.setTenantId(tenantId);
        List<RoleId> roleIdList = new ArrayList<>();
        roleIdList.add(new RoleId(roleId));
        userService.addUser(new Mobile(mobile), null, Password.create(Password.DEFAULT), new UserName(userName), roleIdList );
    }
}
