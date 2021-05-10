package com.xtoon.boot.sys.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志Mapper
 *
 * @author haoxin
 * @date 2021-01-23
 **/
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogDO> {

}
