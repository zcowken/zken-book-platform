package com.books.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContributePageRequestDTO {
    private Integer reviewResult; // 审核结果 -- review_result
    private Integer recommended; // 推荐等级

    private String title; // 稿件名称

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;


}
