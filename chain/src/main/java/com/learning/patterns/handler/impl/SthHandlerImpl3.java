package com.learning.patterns.handler.impl;

import com.learning.patterns.handler.AbstractSthHandler;
import com.learning.patterns.sth.SthRequest;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
@Component
public class SthHandlerImpl3 extends AbstractSthHandler {

    @Override
    public void doSth(SthRequest sthRequest) {
        System.out.println("SthHandlerImpl3 begin check");
    }
}
