package com.xtoon.boot.application.impl;

import com.xtoon.boot.application.AccountService;
import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.domain.model.user.types.AccountId;
import com.xtoon.boot.domain.model.user.types.AccountName;
import com.xtoon.boot.domain.model.user.types.Mobile;
import com.xtoon.boot.domain.model.user.types.Password;
import com.xtoon.boot.domain.repository.AccountRepository;
import com.xtoon.boot.domain.specification.LoginByAccountSpecification;
import com.xtoon.boot.domain.specification.LoginByMobileSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账号ServiceImpl
 *
 * @author haoxin
 * @date 2021-02-21
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Account login(AccountName accountName, String password) {
        Account account = accountRepository.find(accountName);
        LoginByAccountSpecification loginByUserNameSpecification = new LoginByAccountSpecification(password);
        loginByUserNameSpecification.isSatisfiedBy(account);
        accountRepository.store(account.updateToken());
        return account;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Account login(Mobile mobile) {
        Account account = accountRepository.find(mobile);
        LoginByMobileSpecification loginByMobileSpecification = new LoginByMobileSpecification();
        loginByMobileSpecification.isSatisfiedBy(account);
        accountRepository.store(account.updateToken());
        return account;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(AccountId accountId) {
        Account account = accountRepository.find(accountId);
        accountRepository.store(account.updateToken());
    }

    @Override
    public void changePassword(Account account, String oldPasswordStr, String newPasswordStr) {
        Account newAccount = account.changePassword(oldPasswordStr, newPasswordStr);
        accountRepository.store(newAccount);
    }
}
