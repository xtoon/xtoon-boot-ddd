package com.xtoon.boot.interfaces.sys.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysCaptchaDO;
import com.xtoon.boot.infrastructure.persistence.mybatis.mapper.SysCaptchaMapper;
import com.xtoon.boot.infrastructure.util.DateUtils;
import com.xtoon.boot.infrastructure.util.exception.XTException;
import com.xtoon.boot.interfaces.sys.facade.SysCaptchaServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 验证码FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Component
public class SysCaptchaServiceFacadeImpl implements SysCaptchaServiceFacade {

    @Autowired
    private Producer producer;

    @Autowired
    private SysCaptchaMapper sysCaptchaMapper;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new XTException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();

        SysCaptchaDO captchaDO = new SysCaptchaDO();
        captchaDO.setUuid(uuid);
        captchaDO.setCode(code);
        //5分钟后过期
        captchaDO.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        sysCaptchaMapper.insert(captchaDO);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        SysCaptchaDO captchaDO = sysCaptchaMapper.selectOne(new QueryWrapper<SysCaptchaDO>().eq("uuid", uuid));
        if(captchaDO == null){
            return false;
        }

        //删除验证码
        sysCaptchaMapper.deleteById(uuid);

        if(captchaDO.getCode().equalsIgnoreCase(code) && captchaDO.getExpireTime().getTime() >= System.currentTimeMillis()){
            return true;
        }

        return false;
    }
}
