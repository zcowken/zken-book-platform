package com.books.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.books.constant.MessageConstant;
import com.books.dto.UserLoginDTO;
import com.books.entity.User;
import com.books.exception.LoginFailedException;
import com.books.mapper.UserMapper;
import com.books.properties.WeChatProperties;
import com.books.service.UserService;
import com.books.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterAbortException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;

    final String grant_type = "authorization_code";

    final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session"; // 微信的openid的获取网址


    @Override
    public void register(User user) {
        // 添加时间
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
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
        if (userRet == null) {
            throw new LoginFailedException("用户不存在或密码错误");
        }
        return userRet;
    }

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 解压获取的json字符串，获取字段是 openid
        String openid = getOpenid(userLoginDTO.getCode());

        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // 不为空，检测是否为新用户
        User user = userMapper.getByOpenid(openid);
        // 用户不存在的情况加入新用户
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .build();
            user.setName("新用户");
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
        }

        return user;
    }

    @Override
    public User getById(Integer userId) {
        return  userMapper.getUserById(userId);
    }

    private String getOpenid(String js_code) {
        HashMap hashMap = new HashMap();
        // 设置请求的参数，这里设置的是get请求的参数（带着？的部分的参数）
        hashMap.put("appid", weChatProperties.getAppid());
        hashMap.put("secret", weChatProperties.getSecret());
        hashMap.put("js_code", js_code);
        hashMap.put("grant_type", grant_type);
        // 使用自封工具类获取openid
        String res_json = HttpClientUtil.doGet(WX_LOGIN, hashMap);
        // 解压获取的json字符串，获取字段是 openid
        JSONObject jsonObject = JSONObject.parseObject(res_json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
