package com.xtoon.boot.interfaces.facade;

import com.xtoon.boot.domain.shared.Page;

import java.util.Map;

/**
 * 系统日志facade
 *
 * @author haoxin
 * @date 2021-02-04
 **/
public interface SysLogServiceFacade {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page queryPage(Map<String, Object> params);
}
