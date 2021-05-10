package com.xtoon.boot.org.domain.model.post;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 岗位ID
 *
 * @author haoxin
 * @date 2021-05-07
 **/
public class PostId implements ValueObject<PostId> {

    private String id;

    public PostId(final String id) {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("岗位id不能为空");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(PostId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
