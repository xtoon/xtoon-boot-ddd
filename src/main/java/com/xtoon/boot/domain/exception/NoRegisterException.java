package com.xtoon.boot.domain.exception;

/**
 * 未注册异常
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public class NoRegisterException extends IllegalArgumentException {

    public NoRegisterException(String s) {
        super(s);
    }
}
