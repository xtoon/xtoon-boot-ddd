package com.xtoon.boot.sys.interfaces.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.common.web.Page;
import com.xtoon.boot.common.util.mybatis.Query;
import com.xtoon.boot.sys.interfaces.facade.assembler.PageAssembler;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.entity.SysLogDO;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.mapper.SysLogMapper;
import com.xtoon.boot.sys.interfaces.facade.SysLogServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 日志FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-04
 **/
@Component
public class SysLogServiceFacadeImpl implements SysLogServiceFacade {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<SysLogDO> page = sysLogMapper.selectPage(
                new Query<SysLogDO>().getPage(params),
                new QueryWrapper<SysLogDO>().like(StringUtils.isNotBlank(key),"username", key)
        );
        return PageAssembler.toPage(page);
    }
}
