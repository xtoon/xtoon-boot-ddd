package com.xtoon.boot.util;

import com.google.gson.Gson;
import com.xtoon.boot.sys.facade.dto.UserDTO;
import com.xtoon.boot.common.util.HttpContextUtils;
import com.xtoon.boot.common.util.IPUtils;
import com.xtoon.boot.common.util.log.SysLog;
import com.xtoon.boot.sys.domain.model.Log;
import com.xtoon.boot.sys.domain.model.types.UserName;
import com.xtoon.boot.sys.domain.repository.LogRepository;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统日志，切面处理类
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@Aspect
@Component
public class SysLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogRepository logRepository;

    @Pointcut("@annotation(com.xtoon.boot.common.util.log.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        try {
            //保存日志
            saveSysLog(point, time);
        } catch (Exception e) {
            logger.error("保存日志失败："+e.getMessage());
        }

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog syslog = method.getAnnotation(SysLog.class);
        String operation = null;
        if(syslog != null){
            //注解上的描述
            operation = syslog.value();
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String methodString = className + "." + methodName + "()";

        //请求的参数
        String params = null;
        Object[] args = joinPoint.getArgs();
        try{
            params = new Gson().toJson(args);
        } catch (Exception e){
            logger.error(e.getMessage());
        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        String ip = IPUtils.getIpAddr(request);

        //用户名
        UserName userName = null;
        try{
            String userNameStr = ((UserDTO) SecurityUtils.getSubject().getPrincipal()).getUserName();
            userName = new UserName(userNameStr);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        Log log = new Log(null,userName, operation, methodString, params, time, ip);
        //保存系统日志
        logRepository.store(log);
    }
}
