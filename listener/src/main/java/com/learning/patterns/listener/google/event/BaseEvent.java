package com.learning.patterns.listener.google.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzhen
 * @date 2020/9/22
 */
@Data
public class BaseEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The Event id.
     */
    private String eventId;
}
