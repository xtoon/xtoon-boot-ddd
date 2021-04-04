package com.xtoon.boot.interfaces.facade.assembler;

import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.interfaces.facade.dto.LoginSuccessDTO;

/**
 * Assembler class for the LoginSuccessDTOAssembler.
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public class LoginSuccessDTOAssembler {

    public static LoginSuccessDTO toDTO(final User user) {
        final LoginSuccessDTO dto = new LoginSuccessDTO(
                user.getAccount().getToken().getToken(),
                String.valueOf(user.getAccount().getToken().getExpirePeriod()),
                user.getTenantId().getId());
        return dto;
    }
}
