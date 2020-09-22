package com.learning.patterns.listener.google.event;

import com.google.common.eventbus.EventBus;
import com.learning.patterns.listener.google.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * 事件发布器
 *
 * @author wangzhen
 * @date 2020/9/22
 */
@Component
@Slf4j
@SuppressWarnings("UnstableApiUsage")
public final class EventPublisher {
    @Resource
    private EventBus syncEventBus;
    @Resource
    private EventBus asyncEventBus;

    /**
     * 异步事件
     *
     * @param event the event
     */
    public void publishAsync(BaseEvent event) {
        publish(event, true);
    }

    /**
     * 同步事件
     *
     * @param event the event
     */
    public void publish(BaseEvent event) {
        publish(event, false);
    }

    private void publish(BaseEvent event, Boolean isAsync) {
        if (null == event) {
            log.warn("publish event is null");
            return;
        }

        event.setEventId(UuidUtil.create());
        log.info("publish event: eventId=[{}], event=[{}]", event.getEventId(), event);

        try {
            EventBus eventBus = isAsync ? asyncEventBus : syncEventBus;
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        eventBus.post(event);
                    }
                });
            } else {
                eventBus.post(event);
            }
        } catch (Exception e) {
            log.error(String.format("publish event error: reason=%s, eventId=%s, payload=%s", e.getMessage(), event.getEventId(), event), e);
        }
    }
}
