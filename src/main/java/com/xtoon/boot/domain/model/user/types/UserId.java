package com.xtoon.boot.domain.model.user.types;

import com.xtoon.boot.domain.shared.ValueObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户ID
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Data
public class UserId implements ValueObject<UserId> {

    private String id;

    public UserId(final String id) {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("用户id不能为空");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(UserId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
