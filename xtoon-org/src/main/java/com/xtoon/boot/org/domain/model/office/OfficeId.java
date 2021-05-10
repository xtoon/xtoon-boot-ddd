package com.xtoon.boot.org.domain.model.office;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 机构ID
 *
 * @author haoxin
 * @date 2021-05-07
 **/
public class OfficeId implements ValueObject<OfficeId> {

    private String id;

    public OfficeId(final String id) {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("机构id不能为空");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(OfficeId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
