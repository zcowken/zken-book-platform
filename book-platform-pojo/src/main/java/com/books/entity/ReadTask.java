package com.books.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.SimpleTimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadTask {// 关系实体
    Integer id;
    Integer booksId;
    Integer teacherId;
    String taskTitle;
    String taskSuggestion;
    String taskDetail;
    LocalDateTime createTime; // 发布时间
    LocalDateTime deadline;
}
