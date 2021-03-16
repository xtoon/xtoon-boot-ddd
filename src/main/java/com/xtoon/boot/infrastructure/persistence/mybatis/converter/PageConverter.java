package com.xtoon.boot.infrastructure.persistence.mybatis.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.domain.shared.Page;

/**
 * 分页转换类
 *
 * @author haoxin
 * @date 2021-02-04
 **/
public class PageConverter {

    public static Page toPage(IPage iPage) {
        Page page = new Page(iPage.getRecords(), iPage.getTotal(), iPage.getSize(), iPage.getCurrent());
        return page;
    }
}
