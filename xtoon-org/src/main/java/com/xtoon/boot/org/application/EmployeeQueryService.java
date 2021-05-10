package com.xtoon.boot.org.application;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.org.application.dto.EmployeeDTO;

import java.util.Map;

/**
 * 员工查询服务
 *
 * @author haoxin
 * @date 2021-05-10
 **/
public interface EmployeeQueryService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    EmployeeDTO getById(String id);
}
