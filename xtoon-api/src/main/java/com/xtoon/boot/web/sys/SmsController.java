package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.exception.XTException;
import com.xtoon.boot.util.log.SysLog;
import com.xtoon.boot.common.util.message.AliSmsUtils;
import com.xtoon.boot.common.util.redis.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信Controller
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Api(tags = "短信")
@RestController
@RequestMapping("/sys/sms")
public class SmsController {

    public static final long PHONE_CODE_TIME = 5 * 60;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AliSmsUtils aliSmsUtils;

    @ApiOperation("发送验证吗")
    @SysLog("发送验证吗")
    @GetMapping("/getVerificationCode")
    public Result getVerificationCode(@ApiParam(name="phone",value="手机号",required=true) String phone){
        // 生成6位随机数
        String verificationCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        // 发送验证码
        boolean success = aliSmsUtils.sendVerificationCode(phone.trim(), verificationCode);
        if (success) {
            // 发送成功，将手机号和验证码放入redis中，有效期5分钟
            redisUtils.set(CommonConstant.REDIS_PHONE_CODE + phone, verificationCode,
                    PHONE_CODE_TIME);
        } else {
            throw new XTException("发送失败");
        }
        return Result.ok();
    }
}
