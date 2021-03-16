package com.xtoon.boot.infrastructure.util.validator;

import com.xtoon.boot.infrastructure.util.exception.XTException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author haoxin
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new XTException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new XTException(message);
        }
    }
}
