package com.xtoon.boot.org.application.impl;

import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.org.application.OfficeQueryService;
import com.xtoon.boot.org.application.dto.OfficeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 机构查询服务实现
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class OfficeQueryServiceImpl implements OfficeQueryService {
    @Override
    public Page queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<OfficeDTO> listAll() {
        return null;
    }

    @Override
    public OfficeDTO getById(String id) {
        return null;
    }
}
