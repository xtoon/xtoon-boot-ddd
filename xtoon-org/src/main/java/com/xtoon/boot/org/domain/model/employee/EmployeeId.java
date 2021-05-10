package com.xtoon.boot.org.domain.model.employee;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 员工ID
 *
 * @author haoxin
 * @date 2021-05-07
 **/
public class EmployeeId implements ValueObject<EmployeeId> {

    private String id;

    public EmployeeId(final String id) {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("员工id不能为空");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(EmployeeId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
