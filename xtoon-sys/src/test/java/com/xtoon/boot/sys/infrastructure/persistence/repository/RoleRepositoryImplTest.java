package com.xtoon.boot.sys.infrastructure.persistence.repository;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.ApplicationTest;
import com.xtoon.boot.sys.domain.model.role.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 角色RepositoryImpl测试类
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationTest.class)
@DisplayName("角色RepositoryImpl测试类")
class RoleRepositoryImplTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transactional
    @DisplayName("通过角色ID查找")
    void find() {
        Role role = new Role(null, new RoleCode("test11"),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        RoleId roleId = roleRepository.store(role);
        assertNotNull(roleRepository.find(roleId));
    }

    @Test
    @Transactional
    @DisplayName("通过角色名称查找")
    void find1() {
        Role role = new Role(null, new RoleCode("test11"),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        roleRepository.store(role);
        assertNotNull(roleRepository.find(new RoleName("测试11")));
    }

    @Test
    @Transactional
    @DisplayName("通过角色编码查找")
    void find2() {
        Role role = new Role(null, new RoleCode("test11"),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        roleRepository.store(role);
        assertNotNull(roleRepository.find(new RoleCode("test11")));
    }

    @Test
    @Transactional
    @DisplayName("保存")
    void store() {
        Role role = new Role(null, new RoleCode("test11"),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        RoleId roleId = roleRepository.store(role);
        assertNotNull(roleId);
    }

    @Test
    @Transactional
    @DisplayName("删除")
    void remove() {
        Role role = new Role(null, new RoleCode("test11"),new RoleName("测试11"),"", StatusEnum.ENABLE,null);
        RoleId roleId = roleRepository.store(role);
        List<RoleId> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        roleRepository.remove(roleIds);
        assertNull(roleRepository.find(roleId));
    }
}