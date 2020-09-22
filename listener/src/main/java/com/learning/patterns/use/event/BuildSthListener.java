package com.learning.patterns.use.event;

import com.google.common.eventbus.Subscribe;
import com.learning.patterns.listener.google.event.BaseListener;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen
 * @date 2020/9/22
 */
@Component
public class BuildSthListener extends BaseListener {

    @Subscribe
    public void subscribe(BuildSthEvent event) {
        System.out.println("BuildSthListener:" + event.getEventId());
    }
}
