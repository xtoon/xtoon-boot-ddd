package com.xtoon.boot.interfaces.facade.assembler;

import com.xtoon.boot.domain.model.user.Account;
import com.xtoon.boot.interfaces.facade.dto.LoginSuccessDTO;

/**
 * Assembler class for the LoginSuccessDTOAssembler.
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public class LoginSuccessDTOAssembler {

    public static LoginSuccessDTO toDTO(final Account account) {
        final LoginSuccessDTO dto = new LoginSuccessDTO(
                account.getToken().getToken(),
                String.valueOf(account.getToken().getExpirePeriod()),
                account.getLoginTenantId().getId());
        return dto;
    }
}
