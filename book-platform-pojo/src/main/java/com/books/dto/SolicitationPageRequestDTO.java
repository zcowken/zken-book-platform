package com.books.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitationPageRequestDTO {
    private String title; // 任务名称

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
