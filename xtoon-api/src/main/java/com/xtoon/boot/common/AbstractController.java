package com.xtoon.boot.common;

import com.xtoon.boot.sys.application.dto.UserDTO;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共Controller
 *
 * @author haoxin
 * @date 2021-02-04
 **/
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected UserDTO getUser() {
        return (UserDTO) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getId();
    }
}
