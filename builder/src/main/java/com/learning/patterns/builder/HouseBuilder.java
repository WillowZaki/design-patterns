package com.learning.patterns.builder;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
public interface HouseBuilder {

    void init(Material material);

    void buildBase();

    void buildHead();

    void buildBody();

    void buildFoot();

    House build();
}
