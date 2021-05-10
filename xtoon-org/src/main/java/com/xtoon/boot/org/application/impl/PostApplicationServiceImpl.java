package com.xtoon.boot.org.application.impl;

import com.xtoon.boot.org.application.PostApplicationService;
import com.xtoon.boot.org.application.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位应用服务实现
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Service
public class PostApplicationServiceImpl implements PostApplicationService {

    @Override
    public void saveOrUpdate(PostDTO postDTO) {

    }

    @Override
    public void deleteBatch(List<String> ids) {

    }

    @Override
    public void disable(String id) {

    }
}
