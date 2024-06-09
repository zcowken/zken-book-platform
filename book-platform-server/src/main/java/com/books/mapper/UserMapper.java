package com.books.mapper;

import com.books.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void register(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    User login(User user);
}
