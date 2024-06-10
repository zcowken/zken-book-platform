package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Solicitation {

    private Integer id; // id
    private Integer teacherId; // 教师ID
    private String coverUrl; // 封面
    private String title;// 标题
    private String introduction; // 简介
    private String requirements; // 要求
    private String solicitationContent; // 征收内容（征收的宣言和广告正文）
}
