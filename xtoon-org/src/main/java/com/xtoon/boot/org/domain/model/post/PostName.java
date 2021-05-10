package com.xtoon.boot.org.domain.model.post;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 岗位名称
 *
 * @author haoxin
 * @date 2021-05-07
 **/
public class PostName implements ValueObject<PostName> {

    /**
     * 岗位名称
     */
    private String name;

    public PostName(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("岗位名称不能为空");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(PostName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
