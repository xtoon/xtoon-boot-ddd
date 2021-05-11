package com.xtoon.boot.web.common;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author haoxin
 * @date 2021-01-23
 **/
@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final Integer INTERNAL_SERVER_ERROR_500 = 500;

    public static final Integer OK_200 = 200;

    public Result() {
        put("code", 0);
        put("msg", "success");
    }

    public static Result error() {
        return error(INTERNAL_SERVER_ERROR_500, "服务器异常");
    }

    public static Result error(String msg) {
        return error(INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Result ok(Object data) {
        Result r = new Result();
        r.put("data", data);
        return r;
    }
}
