package com.xtoon.boot.org.application;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.org.application.dto.OfficeDTO;

import java.util.List;
import java.util.Map;

/**
 * 机构查询服务接口
 *
 * @author haoxin
 * @date 2021-05-10
 **/
public interface OfficeQueryService {

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
    List<OfficeDTO> listAll();

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    OfficeDTO getById(String id);
}
