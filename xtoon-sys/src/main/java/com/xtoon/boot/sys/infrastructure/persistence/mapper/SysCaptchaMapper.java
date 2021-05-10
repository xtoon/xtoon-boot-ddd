package com.xtoon.boot.sys.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtoon.boot.sys.infrastructure.persistence.entity.SysCaptchaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码Mapper
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptchaDO> {
}
