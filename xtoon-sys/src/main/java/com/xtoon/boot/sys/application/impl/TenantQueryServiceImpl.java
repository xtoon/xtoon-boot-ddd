package com.xtoon.boot.sys.application.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.common.util.PageAssembler;
import com.xtoon.boot.common.util.mybatis.Query;
import com.xtoon.boot.sys.application.TenantQueryService;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysTenantDO;
import com.xtoon.boot.sys.infrastructure.persistence.mapper.SysTenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 租户查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class TenantQueryServiceImpl implements TenantQueryService {

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysTenantDO> page = sysTenantMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }
}
