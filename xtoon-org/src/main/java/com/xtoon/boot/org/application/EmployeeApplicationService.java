package com.xtoon.boot.org.application;

import com.xtoon.boot.org.application.dto.EmployeeDTO;

import java.util.List;

/**
 * 员工应用服务接口
 *
 * @author haoxin
 * @date 2021-05-06
 **/
public interface EmployeeApplicationService {

    /**
     * 保存
     *
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 更新
     *
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);
}
