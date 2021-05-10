package com.xtoon.boot.sys.application.impl;

import com.xtoon.boot.common.util.TenantContext;
import com.xtoon.boot.sys.application.UserApplicationService;
import com.xtoon.boot.sys.application.assembler.UserDTOAssembler;
import com.xtoon.boot.sys.application.dto.UserDTO;
import com.xtoon.boot.sys.domain.model.user.UserFactory;
import com.xtoon.boot.sys.domain.model.role.RoleId;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import com.xtoon.boot.sys.domain.model.user.*;
import com.xtoon.boot.sys.domain.model.tenant.TenantRepository;
import com.xtoon.boot.sys.domain.model.user.UserRepository;
import com.xtoon.boot.sys.domain.specification.UserUpdateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户应用服务实现类
 *
 * @author haoxin
 * @date 2021-02-09
 **/
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserDTO userDTO) {
        List<RoleId> roleIdList = new ArrayList<>();
        if(userDTO.getRoleIdList() != null) {
            userDTO.getRoleIdList().forEach(roleId -> {
                roleIdList.add(new RoleId(roleId));
            });
        }
        User user = userFactory.createUser(new Mobile(userDTO.getMobile()), new Email(userDTO.getEmail()), Password.create(Password.DEFAULT),
                new UserName(userDTO.getUserName()), roleIdList,new TenantId(TenantContext.getTenantId()));
        userRepository.store(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDTO userDTO) {
        userRepository.store(UserDTOAssembler.toUser(userDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<String> ids) {
        List<UserId> userIds= new ArrayList<>();
        ids.forEach(id -> {
            userIds.add(new UserId(id));
        });
        UserUpdateSpecification userUpdateSpecification = new UserUpdateSpecification(tenantRepository);
        for(UserId userId:userIds) {
            User user = userRepository.find(userId);
            userUpdateSpecification.isSatisfiedBy(user);
        }
        userRepository.remove(userIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(String id) {
        User user = userRepository.find(new UserId(id));
        UserUpdateSpecification userUpdateSpecification = new UserUpdateSpecification(tenantRepository);
        userUpdateSpecification.isSatisfiedBy(user);
        user.disable();
        userRepository.store(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String userId, String oldPasswordStr, String newPasswordStr) {
        User user = userRepository.find(new UserId(userId));
        user.changePassword(oldPasswordStr, newPasswordStr);
        userRepository.store(user);
    }
}
