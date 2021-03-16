package com.xtoon.boot.infrastructure.persistence.mybatis.converter;

import com.xtoon.boot.domain.model.system.Log;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysLogDO;
import org.springframework.beans.BeanUtils;

/**
 * 日志转换类
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public class LogConverter {

    public static SysLogDO fromLog(Log log) {
        SysLogDO sysLogDO = new SysLogDO();
        BeanUtils.copyProperties(log, sysLogDO);
        return sysLogDO;
    }

    public static Log toLog(SysLogDO sysLogDO) {
        Log log = new Log();
        BeanUtils.copyProperties(sysLogDO, log);
        return log;
    }
}
