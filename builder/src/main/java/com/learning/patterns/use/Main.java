package com.learning.patterns.use;

import com.learning.patterns.builder.DefaultHouseBuilder;
import com.learning.patterns.builder.House;
import com.learning.patterns.builder.HouseDirector;
import com.learning.patterns.builder.Material;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@RestController
@RequestMapping("build")
public class Main {
    @Resource
    private DefaultHouseBuilder defaultHouseBuilder;

    @PostMapping("/test")
    public Object test(Material material) throws ExecutionException, InterruptedException {
        defaultHouseBuilder.init(material);
        House house = HouseDirector.create(defaultHouseBuilder);
        System.out.println(house);
        return "ok";
    }
}
