package com.xtoon.boot.org.application.impl;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.org.application.EmployeeQueryService;
import com.xtoon.boot.org.application.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 员工查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    @Override
    public Page queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public EmployeeDTO getById(String id) {
        return null;
    }
}
