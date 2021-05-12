package com.xtoon.boot.sys.domain.specification;

import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.sys.domain.model.role.RoleId;
import com.xtoon.boot.sys.domain.model.tenant.TenantId;
import com.xtoon.boot.sys.domain.model.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号登录Specification测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("账号登录Specification测试")
class LoginByAccountSpecificationTest {

    @Test
    @DisplayName("校验")
    void isSatisfiedBy() {
        Password password = Password.create("123456","11111");
        Account account = new Account(new Mobile("18555555551"),new Email("2323@qq.com"),password);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(new UserId("1"), new UserName("test"), StatusEnum.ENABLE, account, new TenantId("2"), roles);

        LoginByAccountSpecification loginByUserNameSpecification = new LoginByAccountSpecification("123456");
        loginByUserNameSpecification.isSatisfiedBy(user);
    }
}