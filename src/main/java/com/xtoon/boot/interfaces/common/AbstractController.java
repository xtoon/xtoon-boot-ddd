package com.xtoon.boot.interfaces.common;

import com.xtoon.boot.domain.model.user.User;
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

    public static final String RESULT_PAGE = "page";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getUserId().getId();
    }
}
