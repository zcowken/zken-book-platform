package com.books.controller.user;


import com.books.constant.JwtClaimsConstant;
import com.books.entity.User;
import com.books.properties.JwtProperties;
import com.books.result.Result;
import com.books.service.UserService;
import com.books.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("")
    Result<String> userRegister(@RequestBody User user) {
        log.info("用户注册");
        userService.register(user);
        return Result.success();
    }


    /**
     * 用户信息获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 用户修改
     */
    @PutMapping("")
    Result<String> updateUser(@RequestBody User user) {
        log.info("更新用户信息:", user);
        userService.updateUser(user);
        return Result.success();
    }

    @PostMapping("/login")
    Result<User> Login(@RequestBody User user) {

        log.info("用户登录：{}", user);

        User userLogin = userService.login(user); // 此时会把id返还回来
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userLogin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        User userReturn = User.builder().id(userLogin.getId())
                .account(userLogin.getAccount())
                .password(userLogin.getPassword())
                .phone(userLogin.getPhone())
                .token(token).build();

        return Result.success(userReturn);
    }


    @PostMapping("/logout")
    Result<String> logout() {
        log.info("用户退出登录：{}");
        return Result.success();
    }
}
