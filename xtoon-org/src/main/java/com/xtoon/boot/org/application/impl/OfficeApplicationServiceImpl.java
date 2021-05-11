package com.xtoon.boot.org.application.impl;

import com.xtoon.boot.org.application.OfficeApplicationService;
import com.xtoon.boot.org.application.command.OfficeCommand;
import com.xtoon.boot.org.application.dto.OfficeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构应用服务实现
 *
 * @author haoxin
 * @date 2021-05-07
 **/
@Service
public class OfficeApplicationServiceImpl implements OfficeApplicationService {

    @Override
    public void saveOrUpdate(OfficeCommand officeCommand) {

    }

    @Override
    public void deleteBatch(List<String> ids) {

    }

    @Override
    public void disable(String id) {

    }
}
