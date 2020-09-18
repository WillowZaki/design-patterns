package com.learning.patterns.handler;

import com.learning.patterns.request.EventRequest;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
public interface EventHandler {
    /**
     * set next handler
     *
     * @param nextHandler the next handler
     */
    void setNext(EventHandler nextHandler);

    /**
     * 返回true时才往下传递
     *
     * @param eventRequest the eventRequest
     * @return the result of this handler
     */
    boolean process(EventRequest eventRequest);
}
