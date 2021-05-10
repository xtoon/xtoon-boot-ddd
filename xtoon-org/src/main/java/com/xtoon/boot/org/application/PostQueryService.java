package com.xtoon.boot.org.application;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.org.application.dto.PostDTO;

import java.util.List;
import java.util.Map;

/**
 * 岗位查询服务接口
 *
 * @author haoxin
 * @date 2021-05-10
 **/
public interface PostQueryService {

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
    List<PostDTO> listAll();

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    PostDTO getById(String id);
}
