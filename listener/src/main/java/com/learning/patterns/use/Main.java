package com.learning.patterns.use;

import com.learning.patterns.listener.google.event.EventPublisher;
import com.learning.patterns.use.event.BuildSthEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@RestController
@RequestMapping("listener")
public class Main {
    @Resource
    protected EventPublisher eventPublisher;

    @PostMapping("/test")
    public Object test() {
        eventPublisher.publishAsync(new BuildSthEvent("eventName", "other"));
        return "ok";
    }
}
