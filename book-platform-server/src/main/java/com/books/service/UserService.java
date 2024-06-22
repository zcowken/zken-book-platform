package com.books.service;

import com.books.dto.UserLoginDTO;
import com.books.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    User login(User user);

    User wxLogin(UserLoginDTO userLoginDTO);

    User getById(Integer userId);
}
