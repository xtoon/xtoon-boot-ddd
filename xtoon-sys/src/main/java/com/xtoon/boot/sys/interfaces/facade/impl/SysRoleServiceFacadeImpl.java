package com.xtoon.boot.sys.interfaces.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.common.web.Page;
import com.xtoon.boot.common.domain.StatusEnum;
import com.xtoon.boot.common.util.mybatis.Query;
import com.xtoon.boot.sys.application.RoleApplicationService;
import com.xtoon.boot.sys.interfaces.facade.assembler.PageAssembler;
import com.xtoon.boot.sys.interfaces.facade.assembler.RoleDTOAssembler;
import com.xtoon.boot.sys.domain.model.types.RoleId;
import com.xtoon.boot.sys.domain.repository.RoleRepository;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.entity.SysRoleDO;
import com.xtoon.boot.sys.infrastructure.persistence.mybatis.mapper.SysRoleMapper;
import com.xtoon.boot.sys.interfaces.facade.SysRoleServiceFacade;
import com.xtoon.boot.sys.interfaces.facade.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@Component
public class SysRoleServiceFacadeImpl implements SysRoleServiceFacade {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleApplicationService roleApplicationService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysRoleDO> page = sysRoleMapper.queryList(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }

    @Override
    public List<RoleDTO> listAll() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("status", StatusEnum.ENABLE.getValue());
        return RoleDTOAssembler.getRoleDTOList(sysRoleMapper.queryList(param));
    }

    @Override
    public RoleDTO getById(String id) {
        return RoleDTOAssembler.fromRole(roleRepository.find(new RoleId(id)));
    }

    @Override
    public void saveOrUpdate(RoleDTO roleDTO) {
        roleApplicationService.saveOrUpdate(RoleDTOAssembler.toRole(roleDTO));
    }

    @Override
    public void deleteBatch(List<String> ids) {
        List<RoleId> roleIds= new ArrayList<>();
        ids.forEach(id -> {
            roleIds.add(new RoleId(id));
        });
        roleApplicationService.delete(roleIds);
    }

    @Override
    public void disable(String id) {
        roleApplicationService.disable(new RoleId(id));
    }
}
