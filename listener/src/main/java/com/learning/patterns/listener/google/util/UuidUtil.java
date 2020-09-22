package com.learning.patterns.listener.google.util;

import java.util.UUID;

/**
 * The type Uuid util.
 *
 * @author wangzhen
 * @date 2020/9/22
 */
public final class UuidUtil {
    /**
     * Instantiates a new Uuid util.
     */
    private UuidUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...!");
    }

    /**
     * Create string.
     *
     * @return the string
     */
    public static String create() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}