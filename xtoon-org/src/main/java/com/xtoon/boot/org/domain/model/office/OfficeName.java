package com.xtoon.boot.org.domain.model.office;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 机构名称
 *
 * @author haoxin
 * @date 2021-05-07
 **/
public class OfficeName implements ValueObject<OfficeName> {

    /**
     * 岗位名称
     */
    private String name;

    public OfficeName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("机构名称不能为空");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(OfficeName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
