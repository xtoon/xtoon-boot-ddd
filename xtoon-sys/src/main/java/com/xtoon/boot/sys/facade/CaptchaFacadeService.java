package com.xtoon.boot.sys.facade;

import java.awt.image.BufferedImage;

/**
 * 验证码Facade
 *
 * @author haoxin
 * @date 2021-02-08
 **/
public interface CaptchaFacadeService {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
