package com.learning.patterns.converter;

import com.learning.patterns.bo.UserBo;
import com.learning.patterns.po.Address;
import com.learning.patterns.po.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
//    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * 1.一对一常规转换
     */
    UserBo po2bo(User po);

    /**
     * 2.一对一转换，部分字段需要重新映射--部分字段名不一致、类型不一致
     */
    @Mappings({
            @Mapping(source = "name", target = "username"),
            @Mapping(target = "timezone", expression = "java(com.learning.patterns.enums.TimezoneEnum.create(po.getTimezone()))")
    })
    UserBo po2bo2(User po);

    /**
     * 3.多对一转换
     */
    @Mapping(source = "po.name",target = "username")
    @Mapping(source = "address.houseNo",target = "houseNum")
    UserBo po2bo3(User po, Address address);
}
