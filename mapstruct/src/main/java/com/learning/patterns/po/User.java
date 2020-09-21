package com.learning.patterns.po;

import lombok.Data;

import java.util.Date;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@Data
public class User {
    private String name;

    private Integer age;

    private Date birthday;

    private String timezone;
}
