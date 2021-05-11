package com.xtoon.boot.org.application;

import com.xtoon.boot.org.application.command.PostCommand;
import com.xtoon.boot.org.application.dto.PostDTO;

import java.util.List;

/**
 * 岗位应用服务接口
 *
 * @author haoxin
 * @date 2021-05-06
 **/
public interface PostApplicationService {

    /**
     * 保存或更新
     *
     * @param postCommand
     */
    void saveOrUpdate(PostCommand postCommand);

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
