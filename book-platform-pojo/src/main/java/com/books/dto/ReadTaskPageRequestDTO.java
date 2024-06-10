package com.books.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadTaskPageRequestDTO {
    private String taskTitle; // 任务名称

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
