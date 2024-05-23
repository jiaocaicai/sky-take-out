package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * ClassName: UserMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/11 14:16
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);


    void insert(User user);

    @Select("select * from user where id=#{id}")
    User getById(Long userId);

    Integer countByMap(Map map);
}
