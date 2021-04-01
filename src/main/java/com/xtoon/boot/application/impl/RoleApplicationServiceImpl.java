package com.xtoon.boot.application.impl;

import com.xtoon.boot.application.RoleApplicationService;
import com.xtoon.boot.domain.model.user.Role;
import com.xtoon.boot.domain.model.user.types.RoleId;
import com.xtoon.boot.domain.repository.RoleRepository;
import com.xtoon.boot.domain.specification.RoleCreateSpecification;
import com.xtoon.boot.domain.specification.RoleUpdateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色ServiceImpl
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@Service
public class RoleApplicationServiceImpl implements RoleApplicationService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Role role) {
        RoleCreateSpecification roleCreateSpecification = new RoleCreateSpecification(roleRepository);
        roleCreateSpecification.isSatisfiedBy(role);
        roleRepository.store(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<RoleId> roleIds) {
        RoleUpdateSpecification roleUpdateSpecification = new RoleUpdateSpecification();
        for(RoleId roleId : roleIds) {
            Role role = roleRepository.find(roleId);
            roleUpdateSpecification.isSatisfiedBy(role);
        }
        roleRepository.remove(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(RoleId roleId) {
        Role role = roleRepository.find(roleId);
        RoleUpdateSpecification roleUpdateSpecification = new RoleUpdateSpecification();
        roleUpdateSpecification.isSatisfiedBy(role);
        role.disable();
        roleRepository.store(role);
    }
}
