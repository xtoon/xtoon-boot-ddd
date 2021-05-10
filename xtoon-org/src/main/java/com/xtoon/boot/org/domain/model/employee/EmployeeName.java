package com.xtoon.boot.org.domain.model.employee;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 员工姓名
 *
 * @author haoxin
 * @date 2021-05-07
 **/
public class EmployeeName implements ValueObject<EmployeeName> {

    /**
     * 员工姓名
     */
    private String name;

    public EmployeeName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("员工姓名不能为空");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(EmployeeName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
