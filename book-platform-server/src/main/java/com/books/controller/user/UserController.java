package com.books.controller.user;


import com.books.constant.JwtClaimsConstant;
import com.books.context.BaseContext;
import com.books.dto.UserLoginDTO;
import com.books.entity.User;
import com.books.mapper.UserMapper;
import com.books.properties.JwtProperties;
import com.books.result.Result;
import com.books.service.UserService;
import com.books.utils.JwtUtil;
import com.books.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    @PostMapping("/register")
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
        user.setId(BaseContext.getCurrentId().intValue());
        log.info("更新用户信息:{}", user);
        userService.updateUser(user);
        return Result.success();
    }

    @PostMapping("/login")
    Result<UserLoginVO> Login(@RequestBody User user) {
        log.info("用户登录：{}", user);
        User userLogin = userService.login(user); // 此时会把id返还回来
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userLogin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        // 设置返回值
        UserLoginVO userReturn = UserLoginVO.builder().id(userLogin.getId())
                .account(userLogin.getAccount())
                .password(userLogin.getPassword())
                .phone(userLogin.getPhone())
                .authorization(token)
                .isTeacher(false).build();


        return Result.success(userReturn);
    }


    @PostMapping("/logout")
    Result<String> logout() {
        log.info("用户退出登录：");
        return Result.success();
    }

    @PostMapping("/wxlogin")
    public Result<UserLoginVO> openIdLogin(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户进行登录{}", userLoginDTO);
        User user = userService.wxLogin(userLoginDTO);
        // 为登录的微信用户生成jwt的令牌（公共操作）
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .name(user.getName())
                .profilePicture(user.getProfilePicture())
                .openid(user.getOpenid())
                .authorization(token)
                .isTeacher(false)
                .build();

        // 返回含有token的显示段落
        return Result.success(userLoginVO);
    }

    @PostMapping("/myInfo")
    Result<User> getUserInfo() {
        Integer userId = BaseContext.getCurrentId().intValue();

        log.info("id获取了个人信息:{}", userId);

        User userSql = userService.getById(userId);

        return Result.success(userSql);
    }


}
