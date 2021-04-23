package com.xtoon.boot.sys.application.impl;

import com.xtoon.boot.sys.application.UserApplicationService;
import com.xtoon.boot.sys.domain.external.TokenGeneratorExternalService;
import com.xtoon.boot.sys.domain.factory.UserFactory;
import com.xtoon.boot.sys.domain.model.types.*;
import com.xtoon.boot.sys.domain.model.user.User;
import com.xtoon.boot.sys.domain.repository.TenantRepository;
import com.xtoon.boot.sys.domain.repository.UserRepository;
import com.xtoon.boot.sys.domain.specification.LoginByAccountSpecification;
import com.xtoon.boot.sys.domain.specification.UserUpdateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户ServiceImpl
 *
 * @author haoxin
 * @date 2021-02-09
 **/
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    @Autowired
    private TokenGeneratorExternalService tokenGeneratorExternalService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User login(Mobile mobile, String password) {
        List<User> users = userRepository.find(mobile);
        if(users == null || users.isEmpty()) {
            throw new RuntimeException("用户或密码不正确");
        }
        User user = users.get(0);
        LoginByAccountSpecification loginByUserNameSpecification = new LoginByAccountSpecification(password);
        loginByUserNameSpecification.isSatisfiedBy(user);
        user.refreshToken(tokenGeneratorExternalService.generateValue());
        userRepository.store(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User login(Mobile mobile) {
        List<User> users = userRepository.find(mobile);
        if(users == null || users.isEmpty()) {
            throw new RuntimeException("用户不存在");
        }
        User user = users.get(0);
        user.refreshToken(tokenGeneratorExternalService.generateValue());
        userRepository.store(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(UserId userId) {
        User user = userRepository.find(userId);
        user.refreshToken(tokenGeneratorExternalService.generateValue());
        userRepository.store(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(UserId userId, String oldPasswordStr, String newPasswordStr) {
        User user = userRepository.find(userId);
        user.changePassword(oldPasswordStr, newPasswordStr);
        userRepository.store(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        userRepository.store(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<UserId> userIds) {
        UserUpdateSpecification userUpdateSpecification = new UserUpdateSpecification(tenantRepository);
        for(UserId userId:userIds) {
            User user = userRepository.find(userId);
            userUpdateSpecification.isSatisfiedBy(user);
        }
        userRepository.remove(userIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(UserId userId) {
        User user = userRepository.find(userId);
        UserUpdateSpecification userUpdateSpecification = new UserUpdateSpecification(tenantRepository);
        userUpdateSpecification.isSatisfiedBy(user);
        user.disable();
        userRepository.store(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList, TenantId tenantId) {
        User user = userFactory.createUser(mobile, email, password, userName, roleIdList, tenantId);
        userRepository.store(user);
    }
}
