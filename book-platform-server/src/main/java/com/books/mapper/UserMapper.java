package com.books.mapper;

import com.books.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    void insert(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    User login(User user);
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);
}
