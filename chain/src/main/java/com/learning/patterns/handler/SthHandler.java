package com.learning.patterns.handler;

import com.learning.patterns.sth.SthRequest;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
public interface SthHandler {
    void setNext(SthHandler next);

    void handle(SthRequest sthRequest);
}
