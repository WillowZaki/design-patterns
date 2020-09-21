package com.learning.patterns.bo;

import com.learning.patterns.enums.TimezoneEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@Data
public class UserBo {
    private String username;

    private Integer age;

    private Date birthday;

    private TimezoneEnum timezone;

    private String street;

    private int zipCode;

    private int houseNum;

    private String description;
}
