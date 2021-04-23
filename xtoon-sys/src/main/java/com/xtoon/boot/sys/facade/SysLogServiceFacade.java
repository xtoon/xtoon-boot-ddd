package com.xtoon.boot.sys.facade;

import com.xtoon.boot.common.util.Page;

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
