package com.xtoon.boot.org.domain.model.office;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 部门编号
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class OfficeCode implements ValueObject<OfficeCode> {

    /**
     * 部门编号
     */
    private String code;

    public OfficeCode(String code) {
        if(StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("部门编号不能为空");
        }
        this.code = code;
    }

    public String getName() {
        return code;
    }

    @Override
    public boolean sameValueAs(OfficeCode other) {
        return other != null && this.code.equals(other.code);
    }

    @Override
    public String toString() {
        return code;
    }
}
