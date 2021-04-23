package com.xtoon.boot.sys.facade;

import com.xtoon.boot.sys.facade.dto.UserDTO;
import com.xtoon.boot.sys.facade.dto.PermissionDTO;

import java.util.List;

/**
 * 权限Facade
 *
 * @author haoxin
 * @date 2021-02-17
 **/
public interface PermissionFacadeService {

    /**
     * 所有权限
     *
     * @return
     */
    List<PermissionDTO> listAllPermission();

    /**
     * 所有菜单（不保存按钮）
     *
     * @return
     */
    List<PermissionDTO> listAllMenu();

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    PermissionDTO getById(String id);

    /**
     * 保存或更新
     *
     * @param permissionDTO
     */
    void saveOrUpdate(PermissionDTO permissionDTO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);

    /**
     * 获取权限树
     *
     * @param user
     * @return
     */
    List<PermissionDTO> getUserMenuTree(UserDTO user);
}
