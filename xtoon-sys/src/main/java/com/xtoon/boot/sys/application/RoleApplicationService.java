package com.xtoon.boot.sys.application;

import com.xtoon.boot.sys.application.dto.RoleDTO;

import java.util.List;

/**
 * 角色应用服务接口
 *
 * @author haoxin
 * @date 2021-02-17
 **/
public interface RoleApplicationService {

    /**
     * 保存或更新
     *
     * @param roleDTO
     */
    void saveOrUpdate(RoleDTO roleDTO);

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
