package com.xtoon.boot.web.common;

import com.xtoon.boot.sys.application.dto.UserDTO;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用Controller
 *
 * @author haoxin
 * @date 2021-02-04
 **/
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取当前用户
     *
     * @return
     */
    protected UserDTO getUser() {
        return (UserDTO) SecurityUtils.getSubject().getPrincipal();
    }

}
