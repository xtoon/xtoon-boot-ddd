package com.xtoon.boot.org.domain.model.employee;

import com.xtoon.boot.common.domain.ValueObject;
import org.apache.commons.lang.StringUtils;

/**
 * 性别枚举
 *
 * @author haoxin
 * @date 2021-02-02
 **/
public enum SexEnum implements ValueObject<SexEnum> {

    /**
     * 男
     */
    ENABLE("0","男"),

    /**
     * 女
     */
    DISABLE("1","女");


    private String value;

    private String label;

    SexEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据匹配value的值获取Label
     *
     * @param value
     * @return
     */
    public static String getLabelByValue(String value){
        if(StringUtils.isBlank(value)) {
            return "";
        }
        for (SexEnum s : SexEnum.values()) {
            if(value.equals(s.getValue())){
                return s.getLabel();
            }
        }
        return "";
    }

    /**
     * 获取StatusEnum
     *
     * @param value
     * @return
     */
    public static SexEnum getSexEnum(String value){
        if(StringUtils.isBlank(value)) {
            return null;
        }
        for (SexEnum s : SexEnum.values()) {
            if(value.equals(s.getValue())){
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean sameValueAs(final SexEnum other) {
        return this.equals(other);
    }
}
