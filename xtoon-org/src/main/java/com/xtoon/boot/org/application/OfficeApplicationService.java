package com.xtoon.boot.org.application;

import com.xtoon.boot.org.application.dto.OfficeDTO;

import java.util.List;

/**
 * 机构应用服务接口
 *
 * @author haoxin
 * @date 2021-05-06
 **/
public interface OfficeApplicationService {

    /**
     * 保存或更新
     *
     * @param officeDTO
     */
    void saveOrUpdate(OfficeDTO officeDTO);

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
