package com.xtoon.boot.sys.interfaces.web;

import com.xtoon.boot.common.web.AbstractController;
import com.xtoon.boot.common.web.CommonConstant;
import com.xtoon.boot.common.web.Result;
import com.xtoon.boot.common.util.log.SysLog;
import com.xtoon.boot.common.util.redis.RedisUtils;
import com.xtoon.boot.common.util.validator.ValidatorUtils;
import com.xtoon.boot.sys.interfaces.facade.SysCaptchaServiceFacade;
import com.xtoon.boot.sys.interfaces.facade.SysUserServiceFacade;
import com.xtoon.boot.sys.interfaces.facade.dto.LoginSuccessDTO;
import com.xtoon.boot.sys.interfaces.web.command.AccountLoginCommand;
import com.xtoon.boot.sys.interfaces.web.command.MobileLoginCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录Controller
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Api(tags = "登录")
@RestController
public class SysLoginController extends AbstractController {

    @Autowired
    private SysCaptchaServiceFacade sysCaptchaServiceFacade;

    @Autowired
    private SysUserServiceFacade sysUserServiceFacade;

    @Autowired
    private RedisUtils redisUtils;

    public static final Integer NO_REGISTER = 520;

    /**
     * 验证码
     */
    @ApiOperation("验证码")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = sysCaptchaServiceFacade.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 账号登录
     */
    @ApiOperation("账号登录")
    @SysLog("账号登录")
    @PostMapping("/sys/loginByAccount")
    public Result loginByAccount(@RequestBody AccountLoginCommand accountLoginCommand) {
        ValidatorUtils.validateEntity(accountLoginCommand);
        boolean captcha = sysCaptchaServiceFacade.validate(accountLoginCommand.getUuid(), accountLoginCommand.getCaptcha());
        if(!captcha){
            return Result.error("验证码不正确");
        }
        LoginSuccessDTO loginSuccessDTO = sysUserServiceFacade.loginByAccount(accountLoginCommand.getAccountName(), accountLoginCommand.getPassword());
        return Result.ok(loginSuccessDTO);
    }


    /**
     * 手机号登录
     */
    @ApiOperation("手机号登录")
    @SysLog("手机号登录")
    @PostMapping("/sys/loginByMobile")
    public Result loginByMobile(@RequestBody MobileLoginCommand mobileLoginCommand) {
        ValidatorUtils.validateEntity(mobileLoginCommand);
        String verificationCodeRedis = redisUtils.get(CommonConstant.REDIS_PHONE_CODE + mobileLoginCommand.getMobile());
        if (!mobileLoginCommand.getVerificationCode().equals(verificationCodeRedis)) {
            return Result.error("验证码不正确");
        }
        LoginSuccessDTO loginSuccessDTO = sysUserServiceFacade.loginByMobile(mobileLoginCommand.getMobile());
        return Result.ok(loginSuccessDTO);
    }

    /**
     * 退出
     */
    @ApiOperation("退出")
    @SysLog("退出")
    @PostMapping("/sys/logout")
    public Result logout() {
        sysUserServiceFacade.logout(getUser().getId());
        return Result.ok();
    }

}
