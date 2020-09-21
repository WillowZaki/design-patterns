package com.learning.patterns.po;

import lombok.Data;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@Data
public class Address {
    private String street;
    private int zipCode;
    private int houseNo;
    private String description;
}
