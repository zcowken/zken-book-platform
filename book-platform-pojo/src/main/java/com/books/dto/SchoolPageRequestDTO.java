package com.books.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolPageRequestDTO {
    // 学校名称
    private String schoolName;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
