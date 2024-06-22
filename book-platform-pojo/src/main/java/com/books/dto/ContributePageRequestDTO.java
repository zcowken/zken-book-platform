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
    private Integer reviewResult; // 审核结果 -- review_result（通过或者不通过
    private Integer reviewInfo; // 审核情况（受理或等待中）

    private Integer recommended; // 推荐了吗
    private String title; // 稿件名称

    private Integer userId; // 上传者，添加对上传者的指定

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
