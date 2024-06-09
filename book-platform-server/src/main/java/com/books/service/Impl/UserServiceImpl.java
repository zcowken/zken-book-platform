package com.books.service.Impl;

import com.books.entity.User;
import com.books.mapper.UserMapper;
import com.books.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterAbortException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // 添加时间
        user.setCreateTime(LocalDateTime.now());
        userMapper.register(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        userMapper.updateUser(user1);
    }

    @Override
    public User login(User user) {
        User userRet = userMapper.login(user);
        return userRet;
    }
}
