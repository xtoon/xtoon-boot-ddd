package com.xtoon.boot.web.util.log;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
