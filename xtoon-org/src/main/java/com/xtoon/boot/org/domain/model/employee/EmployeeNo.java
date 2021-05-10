package com.xtoon.boot.org.domain.model.employee;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 员工工号
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class EmployeeNo implements ValueObject<EmployeeNo> {

    /**
     * 员工工号
     */
    private String no;

    public EmployeeNo(String no) {
        if(StringUtils.isEmpty(no)) {
            throw new IllegalArgumentException("员工工号不能为空");
        }
        this.no = no;
    }

    public String getName() {
        return no;
    }

    @Override
    public boolean sameValueAs(EmployeeNo other) {
        return other != null && this.no.equals(other.no);
    }

    @Override
    public String toString() {
        return no;
    }
}
