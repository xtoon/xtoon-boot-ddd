package com.xtoon.boot.org.application.impl;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.org.application.PostQueryService;
import com.xtoon.boot.org.application.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 岗位查询服务类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class PostQueryServiceImpl implements PostQueryService {

    @Override
    public Page queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<PostDTO> listAll() {
        return null;
    }

    @Override
    public PostDTO getById(String id) {
        return null;
    }
}
