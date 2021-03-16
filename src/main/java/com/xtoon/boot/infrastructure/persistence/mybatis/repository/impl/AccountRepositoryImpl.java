package com.xtoon.boot.infrastructure.persistence.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.AccountId;
import com.xtoon.boot.domain.model.user.types.AccountName;
import com.xtoon.boot.domain.model.user.types.Mobile;
import com.xtoon.boot.domain.repository.AccountRepository;
import com.xtoon.boot.domain.shared.StatusEnum;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.AccountConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.converter.UserConverter;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysAccountDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysUserDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysAccountMapper;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账号-Repository实现类
 *
 * @author haoxin
 * @date 2021-02-21
 **/
@Repository
public class AccountRepositoryImpl extends ServiceImpl<SysAccountMapper, SysAccountDO> implements AccountRepository, IService<SysAccountDO> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Account find(AccountId accountId) {
        SysAccountDO sysAccountDO = this.getById(accountId.getId());
        if(sysAccountDO == null) {
            return null;
        }
        Account account = AccountConverter.toAccount(sysAccountDO);
        setUsers(account);
        return account;
    }

    @Override
    public Account find(Mobile mobile) {
        SysAccountDO sysAccountDO = this.getOne(new QueryWrapper<SysAccountDO>().eq("mobile", mobile.getMobile()));
        if(sysAccountDO == null) {
            return null;
        }
        Account account = AccountConverter.toAccount(sysAccountDO);
        setUsers(account);
        return account;
    }

    @Override
    public Account find(AccountName accountName) {
        SysAccountDO sysAccountDO = this.getOne(new QueryWrapper<SysAccountDO>().eq("mobile", accountName.getName()).or().eq("email",accountName.getName()));
        if(sysAccountDO == null) {
            return null;
        }
        Account account = AccountConverter.toAccount(sysAccountDO);
        setUsers(account);
        return account;
    }

    private void setUsers(Account account) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId",account.getAccountId().getId());
        List<SysUserDO> sysUserDOList =  sysUserMapper.queryUserNoTenant(params);
        if(sysUserDOList != null && !sysUserDOList.isEmpty()) {
            List<User> userList = new ArrayList<>();
            for (SysUserDO sysUserDO:sysUserDOList) {
                userList.add(UserConverter.toUser(sysUserDO));
            }
            if(!userList.isEmpty()) {
                account.setUsers(userList);
                account.setLoginTenantId(userList.get(0).getTenant().getTenantId());
            }
        }

    }

    @Override
    public void store(Account account) {
        SysAccountDO sysPermissionDO = AccountConverter.fromAccount(account);
        this.saveOrUpdate(sysPermissionDO);
        account.setAccountId(new AccountId(sysPermissionDO.getId()));
    }
}
