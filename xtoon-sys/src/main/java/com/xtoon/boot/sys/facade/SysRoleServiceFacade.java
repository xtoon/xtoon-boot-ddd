package com.xtoon.boot.sys.facade;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.sys.facade.dto.RoleDTO;

import java.util.List;
import java.util.Map;

/**
 * 角色Facade
 *
 * @author haoxin
 * @date 2021-02-18
 **/
public interface SysRoleServiceFacade {


    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 查询列表
     *
     * @return
     */
    List<RoleDTO> listAll();

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    RoleDTO getById(String id);

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
