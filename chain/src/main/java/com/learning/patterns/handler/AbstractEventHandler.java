package com.learning.patterns.handler;

import com.learning.patterns.request.EventRequest;

import java.util.Objects;

/**
 * 处理器
 *
 * @author wangzhen
 * @date 2020/9/18
 */
public abstract class AbstractEventHandler implements EventHandler {
    /**
     * 当第一次 EventHandlerImpl 执行process时，此处的handler为EventHandlerImpl2的对象，以此类推
     */
    private EventHandler handler;

    @Override
    public void setNext(EventHandler nextHandler) {
        this.handler = nextHandler;
    }

    @Override
    public boolean process(EventRequest eventRequest) {
        System.out.println(handler == null ? "已到达最后一个处理器" : "下个处理器已就绪：" + handler.getClass().getName());
        boolean result = handle(eventRequest);
        // 传递职责，可在此处添加处理器退出或传递规则
        return (Objects.nonNull(handler) && result) && handler.process(eventRequest);
    }

    /**
     * 处理器对应的处理方法
     *
     * @param eventRequest request
     * @return teh handle result
     */
    public abstract boolean handle(EventRequest eventRequest);

}
