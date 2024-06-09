package com.books.controller.teacher;

import com.books.constant.JwtClaimsConstant;
import com.books.entity.Teacher;
import com.books.properties.JwtProperties;
import com.books.result.Result;
import com.books.service.TeacherService;
import com.books.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/teacher/teacher")
@Slf4j

public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 教师注册
     * @param teacher
     * @return
     */
    @PostMapping("")
    Result<String> userRegister(@RequestBody Teacher teacher) {
        log.info("教师注册");
        teacherService.register(teacher);
        return Result.success();
    }


    /**
     * 教师信息获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Teacher> getTeacherById(@PathVariable Integer id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return Result.success(teacher);
    }

    /**
     * 教师修改
     */
    @PutMapping("")
    Result<String> updateTeacher(@RequestBody Teacher teacher) {
        log.info("更新教师信息:", teacher);
        teacherService.updateTeacher(teacher);
        return Result.success();
    }

    @PostMapping("/login")
    Result<Teacher> Login(@RequestBody Teacher teacher) {
        log.info("教师登录：{}", teacher);
        Teacher userLogin = teacherService.login(teacher); // 此时会把id返还回来
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userLogin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        Teacher userReturn = Teacher.builder().id(userLogin.getId())
                .account(userLogin.getAccount())
                .password(userLogin.getPassword())
                .phone(userLogin.getPhone())
                .token(token).build();

        return Result.success(userReturn);
    }


    @PostMapping("/logout")
    Result<String> logout() {
        log.info("教师退出登录：{}");
        return Result.success();
    }
}
