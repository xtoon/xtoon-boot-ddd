package com.xtoon.boot.domain.repository;

import com.xtoon.boot.domain.model.system.Log;

/**
 * 日志-Repository接口
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public interface LogRepository {

    /**
     * 保存日志
     *
     * @param log
     */
    void store(Log log);
}
