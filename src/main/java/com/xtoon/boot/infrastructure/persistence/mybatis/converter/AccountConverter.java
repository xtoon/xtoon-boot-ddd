package com.xtoon.boot.infrastructure.persistence.mybatis.converter;

import com.xtoon.boot.domain.model.system.types.TenantId;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.*;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysAccountDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserDO;
import com.xtoon.boot.infrastructure.util.exception.XTException;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号转换类
 *
 * @author haoxin
 * @date 2021-02-21
 **/
public class AccountConverter {

    public static SysAccountDO fromAccount(Account account) {
        if (account == null) {
            throw new XTException("account is null");
        }
        SysAccountDO sysAccountDO = new SysAccountDO();
        sysAccountDO.setId(account.getAccountId() == null?null:account.getAccountId().getId());
        sysAccountDO.setEmail(account.getEmail() == null?null:account.getEmail().getEmail());
        sysAccountDO.setMobile(account.getMobile() == null?null:account.getMobile().getMobile());
        sysAccountDO.setPassword(account.getPassword()==null?null:account.getPassword().getPassword());
        sysAccountDO.setSalt(account.getPassword()==null?null:account.getPassword().getSalt());
        sysAccountDO.setToken(account.getToken() == null?null:account.getToken().getToken());
        sysAccountDO.setExpireTime(account.getToken()==null?null:account.getToken().getExpireTime());
        return sysAccountDO;
    }


    public static Account toAccount(SysAccountDO sysAccountDO, List<SysUserDO> sysUserDOList) {
        if(sysAccountDO == null) {
            return null;
        }
        Mobile mobile = null;
        if(sysAccountDO.getMobile() != null) {
            mobile = new Mobile(sysAccountDO.getMobile());
        }
        Email email = null;
        if(sysAccountDO.getEmail() != null) {
            email = new Email(sysAccountDO.getEmail());
        }
        Password password = null;
        if(sysAccountDO.getPassword() != null) {
            password = new Password(sysAccountDO.getPassword(), sysAccountDO.getSalt());
        }
        Token token = null;
        if(sysAccountDO.getToken() != null) {
            token = new Token(sysAccountDO.getToken(),sysAccountDO.getExpireTime());
        }
        List<User> users = null;
        TenantId loginTenantId = null;
        if(sysUserDOList != null && !sysUserDOList.isEmpty()) {
            users = new ArrayList<>();
            for (SysUserDO sysUserDO:sysUserDOList) {
                users.add(UserConverter.toUser(sysUserDO,null,null));
            }
            if(!users.isEmpty()) {
                loginTenantId = users.get(0).getTenant().getTenantId();
            }
        }
        Account account = new Account(new AccountId(sysAccountDO.getId()),mobile,email,password,token,users);
        return account;
    }
}
