package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    Integer id;  //  -主键
    String token;

    String name; // -姓名
    String account; //-账户
    String password; //-密码
    String openid; //-外部认证id
    String phone; //-电话号码
    String profilePicture; //-头像
    LocalDateTime createTime; //-账户创建时间

}
