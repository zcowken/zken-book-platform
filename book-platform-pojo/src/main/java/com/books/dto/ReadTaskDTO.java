package com.books.dto;

import com.books.entity.Contribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadTaskDTO {

    Integer id;
    //    Integer booksId; // 暂时不用
    Integer teacherId;
    String taskTitle;
    String taskSuggestion;
    String taskDetail;
    LocalDateTime createTime; // 发布时间
    LocalDateTime deadline;

    List<Contribute> contributes; // 相关的阅读的稿件详情
}
