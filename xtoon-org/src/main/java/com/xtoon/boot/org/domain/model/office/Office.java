package com.xtoon.boot.org.domain.model.office;

import com.xtoon.boot.common.domain.Entity;

/**
 * 机构
 *
 * @author haoxin
 * @date 2021-05-08
 **/
public class Office implements Entity<Office> {

    /**
     * 机构ID
     */
    private OfficeId officeId;

    /**
     * 机构编码
     */
    private OfficeCode officeCode;

    /**
     * 机构名称
     */
    private OfficeName officeName;



    @Override
    public boolean sameIdentityAs(Office other) {
        return false;
    }
}
