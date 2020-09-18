package com.learning.patterns.chain;

import com.learning.patterns.handler.EventHandler;
import com.learning.patterns.request.EventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 责任链
 *
 * @author wangzhen
 * @date 2020/9/18
 */
@Slf4j
public abstract class AbstractResponsibilityChain implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public final void start(EventRequest eventRequest) {
        // 收集所有实现 EventHandler 的类实例
        Map<String, EventHandler> handlerMap = applicationContext.getBeansOfType(EventHandler.class);
        List<EventHandler> handlers = selectAndSortChainNode(handlerMap);
        assemblyChain(handlers, eventRequest);
    }

    /**
     * 负责挑选处理器并将其排好序
     *
     * @param handlers handlers
     * @return handler list
     */
    protected abstract List<EventHandler> selectAndSortChainNode(Map<String, EventHandler> handlers);

    /**
     * 将挑选好的处理器按顺序建立链式关系
     *
     * @param handlers     handler list
     * @param eventRequest request
     */
    private void assemblyChain(List<EventHandler> handlers, EventRequest eventRequest) {
        if (CollectionUtils.isEmpty(handlers)) {
            return;
        }

        for (int i = 0; i < handlers.size() - 1; i++) {
            // 帮助理解：将setNext(..)移到各自实现类里以设置下个处理器
            handlers.get(i).setNext(handlers.get(i + 1));
        }

        System.out.println("第一个处理器名字：" + handlers.get(0).getClass().getName());

        // 顺序执行处理器上方法
        handlers.get(0).process(eventRequest);
    }
}
