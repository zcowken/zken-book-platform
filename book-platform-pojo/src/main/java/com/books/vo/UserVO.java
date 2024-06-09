package com.books.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    Integer id;  //  -主键
    String name; // -姓名
    String account; //-账户
    String password; //-密码
    String openid; //-外部认证id
    String phone; //-电话号码
    String profile_picture; //-头像
    LocalDateTime create_time; //-账户创建时间
}
