package com.xtoon.boot.domain.repository;

import com.xtoon.boot.domain.model.system.Log;
import com.xtoon.boot.domain.shared.Page;

import java.util.Map;

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
    Log store(Log log);
}
