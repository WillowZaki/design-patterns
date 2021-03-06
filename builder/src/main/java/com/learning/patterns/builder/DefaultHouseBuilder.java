package com.learning.patterns.builder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 当HouseBuilder大多数方法比较耗时时推荐结合CompletableFuture使用
 *
 * @author wangzhen
 * @date 2020/9/21
 */
@Component
@Slf4j
public class DefaultHouseBuilder implements HouseBuilder {
    private Material material;

//    private String string;

    private House house;

//    @Resource
//    private Service service;

    @Override
    public void init(Material material) {
        this.material = material;
//        this.string = string.getXX(material.getSomething());
        System.out.println("init ready.");
    }

    @Override
    public void buildBase() {
        house = new House();
        System.out.println("buildBase ready.");
    }

    @Override
    public void buildHead() {
        house.setHead("head");
        System.out.println("buildHead ready.");
    }

    @Override
    public void buildBody() {
        house.setBody("body");
        System.out.println("buildBody ready.");
    }

    @Override
    public void buildFoot() {
        house.setFoot("foot");
        System.out.println("buildFoot ready.");
    }

    @Override
    public House build() {
        return house;
    }
}
