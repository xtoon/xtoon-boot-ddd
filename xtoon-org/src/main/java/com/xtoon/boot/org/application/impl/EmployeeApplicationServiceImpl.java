package com.xtoon.boot.org.application.impl;

import com.xtoon.boot.org.application.EmployeeApplicationService;
import com.xtoon.boot.org.application.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工应用服务实现
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Service
public class EmployeeApplicationServiceImpl implements EmployeeApplicationService {

    @Override
    public void save(EmployeeDTO employeeDTO) {

    }

    @Override
    public void update(EmployeeDTO employeeDTO) {

    }

    @Override
    public void deleteBatch(List<String> ids) {

    }

    @Override
    public void disable(String id) {

    }
}
