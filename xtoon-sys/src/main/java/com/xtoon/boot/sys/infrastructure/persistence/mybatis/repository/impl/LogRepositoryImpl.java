package com.xtoon.boot.sys.infrastructure.persistence.mybatis.repository.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtoon.boot.sys.domain.model.Log;
import com.xtoon.boot.sys.domain.repository.LogRepository;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.entity.SysLogDO;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.mapper.SysLogMapper;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.converter.LogConverter;
import org.springframework.stereotype.Repository;

/**
 * 日志-Repository实现类
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@Repository
public class LogRepositoryImpl extends ServiceImpl<SysLogMapper, SysLogDO> implements LogRepository, IService<SysLogDO> {

    @Override
    public void store(Log log) {
        SysLogDO sysLogDO = LogConverter.fromLog(log);
        if (sysLogDO.getId() == null) {
            this.save(sysLogDO);
        } else {
            this.updateById(sysLogDO);
        }
    }
}
