package com.xtoon.boot.interfaces.sys.facade.impl;

import com.xtoon.boot.application.AccountService;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.types.AccountId;
import com.xtoon.boot.domain.model.user.types.AccountName;
import com.xtoon.boot.domain.model.user.types.Mobile;
import com.xtoon.boot.interfaces.sys.facade.SysAccountServiceFacade;
import com.xtoon.boot.interfaces.sys.facade.assembler.LoginSuccessDTOAssembler;
import com.xtoon.boot.interfaces.sys.facade.dto.LoginSuccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 账号FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-21
 **/
@Component
public class SysAccountServiceFacadeImpl implements SysAccountServiceFacade {

    @Autowired
    private AccountService accountService;

    @Override
    public LoginSuccessDTO loginByAccount(String accountName, String password) {
        Account account = accountService.login(new AccountName(accountName), password);
        return LoginSuccessDTOAssembler.toDTO(account);
    }

    @Override
    public LoginSuccessDTO loginByMobile(String mobile) {
        Account account = accountService.login(new Mobile(mobile));
        return LoginSuccessDTOAssembler.toDTO(account);
    }

    @Override
    public void logout(String accountId) {
        accountService.logout(new AccountId(accountId));
    }
}
