package com.learning.patterns.handler;

import com.learning.patterns.sth.SthRequest;

import java.util.Optional;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
public abstract class AbstractSthHandler implements SthHandler {
    private SthHandler handler;

    @Override
    public void setNext(SthHandler next) {
        this.handler = next;
    }

    @Override
    public void handle(SthRequest sthRequest) {
        doSth(sthRequest);

        // 传递职责，可在此处添加处理器退出或传递规则
        Optional.ofNullable(handler).ifPresent(h -> h.handle(sthRequest));
    }

    /**
     * 处理器对应的处理方法
     *
     * @param sthRequest request
     */
    public abstract void doSth(SthRequest sthRequest);
}
