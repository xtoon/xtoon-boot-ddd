package com.xtoon.boot.interfaces.facade;

import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.UserId;
import com.xtoon.boot.domain.repository.UserRepository;
import com.xtoon.boot.interfaces.facade.dto.PermissionDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 权限Facade测试类
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class SysPermissionServiceFacadeTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysPermissionServiceFacade sysPermissionServiceFacade;

    @Autowired
    private UserRepository userRepository;

    @Test
    void listAllPermission() {
    }

    @Test
    void listAllMenu() {
    }

    @Test
    void getById() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void delete() {
    }

    @Test
    void getUserMenuTree() {
        User user = userRepository.find(new UserId("1"));
        List<PermissionDTO> list = sysPermissionServiceFacade.getUserMenuTree(user);
        logger.info("list="+list);
    }
}