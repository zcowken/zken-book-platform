package com.books.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {


    Integer id;  //  -主键
    String name; // -姓名
    String account; //-账户
    String password; //-密码
    String openid; //-外部认证id
    String phone; //-电话号码
    String profilePicture; //-头像

    // 额外需求信息
    private String authorization;
    private Boolean isTeacher;

}
