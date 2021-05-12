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
 * token登录Specification测试
 *
 * @author haoxin
 * @date 2021-05-12
 **/
@DisplayName("token登录Specification测试")
class LoginByTokenSpecificationTest {

    @Test
    @DisplayName("校验")
    void isSatisfiedBy() {
        Password password = Password.create("123456","abc");
        Token token = Token.create("11");
        Account account = new Account(new AccountId("1"), new Mobile("18666656565"), new Email("34343423@qq.com"), password,token);
        List<RoleId> roles = new ArrayList<>();
        roles.add(new RoleId("1"));
        User user = new User(new UserId("1"), new UserName("test"), StatusEnum.ENABLE, account, new TenantId("2"), roles);

        LoginByTokenSpecification loginByTokenSpecification = new LoginByTokenSpecification();
        loginByTokenSpecification.isSatisfiedBy(user);
    }
}