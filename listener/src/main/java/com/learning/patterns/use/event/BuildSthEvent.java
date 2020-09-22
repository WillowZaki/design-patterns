package com.learning.patterns.use.event;

import com.learning.patterns.listener.google.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangzhen
 * @date 2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildSthEvent extends BaseEvent {
    private String name;

    private String other;
}
