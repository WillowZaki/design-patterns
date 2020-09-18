package com.learning.patterns.handler.impl;

import com.learning.patterns.handler.AbstractEventHandler;
import com.learning.patterns.request.EventRequest;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
@Component
public class EventHandlerImpl extends AbstractEventHandler {

    @Override
    public boolean handle(EventRequest eventRequest) {
        eventRequest.setName("EventHandlerImpl");
        eventRequest.setAge(eventRequest.getAge() + 1);
        System.out.println(eventRequest);
        return true;
    }
}
