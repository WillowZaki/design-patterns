package com.learning.patterns.use;

import com.learning.patterns.chain.EventResponsibilityChain;
import com.learning.patterns.request.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhen
 * @date 2020/9/18
 */
@RestController
@RequestMapping("chain")
public class Main {
    @Autowired
    private EventResponsibilityChain eventResponsibilityChain;

    /**
     * 使用场景
     * 第一种：一个请求可被多个对象中的某个处理，但请求方不关心具体的处理者，此时可使用责任链模式
     * 第二种：一个请求需要经过多个对象处理，如经典的filter模式
     * <p>
     * 使用方式step
     * 1.抽象模型：请求对象，该请求对应的处理者，该请求对应的责任链（负责挑选和排序加入的处理器）
     * 2.
     */

    @PostMapping("/test")
    public Object test() {
        EventRequest eventRequest = new EventRequest("sthRequest", 0);
        try {
            eventResponsibilityChain.start(eventRequest);
        } catch (RuntimeException e) {
            // 通过某个处理器抛异常方式中断链职责
            return "error";
        }
        return "ok";
    }
}
