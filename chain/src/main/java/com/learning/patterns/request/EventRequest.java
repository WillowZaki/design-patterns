package com.learning.patterns.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
@Data
@AllArgsConstructor
public class EventRequest {
    /**
     * 记录经过的处理器名字
     */
    private String name;

    /**
     * 记录经过的处理器个数
     */
    private Integer age;
}
