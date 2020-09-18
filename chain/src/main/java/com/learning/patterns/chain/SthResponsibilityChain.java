package com.learning.patterns.chain;

import com.learning.patterns.handler.SthHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
@Component
public class SthResponsibilityChain extends AbstractResponsibilityChain {
    @Override
    protected List<SthHandler> selectAndSortChainNode(Map<String, SthHandler> handlers) {
        List<SthHandler> list = new ArrayList<>();
        list.add(handlers.get("sthHandlerImpl"));
        list.add(handlers.get("sthHandlerImpl2"));
        list.add(handlers.get("sthHandlerImpl3"));
        return list;
    }
}
