package com.learning.patterns.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yanghuadong
 * @date 2020-03-10
 */
@AllArgsConstructor
@Getter
public enum TimezoneEnum {

    UTC("0", "", 0),
    PLUS_8("+8", "_plus8", 8);

    private String value;
    private String suffix;
    private int intValue;

    public static TimezoneEnum createOrDefault(String value) {
        for (TimezoneEnum v : values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }

        return null;
    }

    public static TimezoneEnum create(String name) {
        for (TimezoneEnum v : values()) {
            if (v.name().equals(name)) {
                return v;
            }
        }

        return null;
    }
}