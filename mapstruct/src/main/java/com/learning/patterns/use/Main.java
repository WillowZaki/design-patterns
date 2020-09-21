package com.learning.patterns.use;
import com.learning.patterns.bo.UserBo;
import com.learning.patterns.converter.UserConverter;
import com.learning.patterns.po.Address;
import com.learning.patterns.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@RestController
@RequestMapping("map")
public class Main {
    @Autowired
    private UserConverter userConverter;

    @PostMapping("/test")
    public Object test() {
        User user = new User();
        user.setName("mapstruct");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setTimezone("PLUS_8");

        Address address = new Address();
        address.setStreet("hangzhou.alibaba");
        address.setZipCode(200);
        address.setHouseNo(1024);
        address.setDescription("here is address");


        UserBo userBo = userConverter.po2bo3(user,address);
        System.out.println(userBo);
        return userBo;
    }
}
