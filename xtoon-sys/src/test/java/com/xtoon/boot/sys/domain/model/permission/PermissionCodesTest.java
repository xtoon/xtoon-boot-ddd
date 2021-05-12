package com.xtoon.boot.sys.domain.model.permission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * PermissionCodesTest
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class PermissionCodesTest {

    Set<String> set = null;

    @BeforeEach
    void setUp() {
        set = new HashSet<>();
        set.add("1");
        set.add("2");
    }

    @Test
    void getCodesString() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PermissionCodes(null);
        });
        assertEquals(new PermissionCodes(set).getCodesString(),"1,2");
    }
}