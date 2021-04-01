package com.xtoon.boot.application.impl;

import com.xtoon.boot.application.UserApplicationService;
import com.xtoon.boot.domain.factory.UserFactory;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.domain.repository.AccountRepository;
import com.xtoon.boot.domain.repository.TenantRepository;
import com.xtoon.boot.domain.repository.UserRepository;
import com.xtoon.boot.domain.specification.UserUpdateSpecification;
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
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private TenantRepository tenantRepository;

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
    public void addUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList) {
        User user = userFactory.createUser(mobile, email, password, userName, roleIdList);
        userRepository.store(user);
    }
}
