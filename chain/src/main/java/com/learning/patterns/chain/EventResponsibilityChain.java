package com.learning.patterns.chain;

import com.learning.patterns.handler.EventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
@Component
public class EventResponsibilityChain extends AbstractResponsibilityChain {
    @Override
    protected List<EventHandler> selectAndSortChainNode(Map<String, EventHandler> handlers) {
        List<EventHandler> list = new ArrayList<>();
        list.add(handlers.get("eventHandlerImpl"));
        list.add(handlers.get("eventHandlerImpl2"));
        list.add(handlers.get("eventHandlerImpl3"));
        return list;
    }
}
