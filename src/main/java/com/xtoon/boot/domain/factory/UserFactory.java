package com.xtoon.boot.domain.factory;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.domain.repository.AccountRepository;
import com.xtoon.boot.domain.repository.RoleRepository;
import com.xtoon.boot.infrastructure.util.mybatis.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户工厂
 *
 * @author haoxin
 * @date 2021-02-24
 **/
@Component
public class UserFactory {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    public User createUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList) {
        Account account = accountRepository.find(mobile);
        if(account != null) {
            String currentTenantId = TenantContext.getTenantId();
            for(User user : account.getUsers()) {
                if(user.getTenant() != null && user.getTenant().getTenantId() != null &&
                        user.getTenant().getTenantId().getId().equals(currentTenantId)) {
                    throw new RuntimeException("租户内账号已存在");
                }
            }
        }
        if(account == null) {
            account = new Account(null,mobile,email,password,null,null);
        }
        if(roleIdList == null || roleIdList.isEmpty()) {
            throw new RuntimeException("角色未分配");
        }
        List<Role> roles = new ArrayList<>();
        for(RoleId roleId:roleIdList) {
            Role role = roleRepository.find(roleId);
            if(role != null) {
                roles.add(role);
            }
        }
        return new User(userName,account,roles);
    }

}
