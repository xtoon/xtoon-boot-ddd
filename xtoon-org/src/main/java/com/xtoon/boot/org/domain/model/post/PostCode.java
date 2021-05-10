package com.xtoon.boot.org.domain.model.post;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 岗位编号
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class PostCode implements ValueObject<PostCode> {

    /**
     * 岗位编号
     */
    private String code;

    public PostCode(String code) {
        if(StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("岗位编号不能为空");
        }
        this.code = code;
    }

    public String getName() {
        return code;
    }

    @Override
    public boolean sameValueAs(PostCode other) {
        return other != null && this.code.equals(other.code);
    }

    @Override
    public String toString() {
        return code;
    }
}
