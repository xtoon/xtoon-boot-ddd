package com.xtoon.boot.sys.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysAccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号Mapper
 *
 * @author haoxin
 * @date 2021-02-10
 **/
@Mapper
public interface SysAccountMapper extends BaseMapper<SysAccountDO> {
}
