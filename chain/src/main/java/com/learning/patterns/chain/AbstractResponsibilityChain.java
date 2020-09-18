package com.learning.patterns.chain;

import com.learning.patterns.handler.SthHandler;
import com.learning.patterns.sth.SthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
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

    public final void startChain(SthRequest sthRequest) {
        Map<String, SthHandler> handlerMap = applicationContext.getBeansOfType(SthHandler.class);
        List<SthHandler> handlerChain = selectAndSortChainNode(handlerMap);
        assemblyChain(handlerChain, sthRequest);
    }

    /**
     * 负责挑选和排序加入的处理器
     *
     * @param handlers handlers
     * @return handler list
     */
    protected abstract List<SthHandler> selectAndSortChainNode(Map<String, SthHandler> handlers);

    /**
     * 将挑选好的处理器按顺序组装
     *
     * @param handlers   handler list
     * @param sthRequest request
     */
    private void assemblyChain(List<SthHandler> handlers, SthRequest sthRequest) {
        Optional.ofNullable(handlers).ifPresent(hs -> {
            for (int i = 0; i < handlers.size() - 1; i++) {
                handlers.get(i).setNext(handlers.get(i + 1));
            }
            // 组装好后从前往后执行处理器的对应方法
            handlers.get(0).handle(sthRequest);
        });
    }
}
