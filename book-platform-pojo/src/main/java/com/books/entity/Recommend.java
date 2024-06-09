package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {
    Integer id;
    Integer teacherId;
    Integer contributeId;
    String reason;
    Integer recommendLevel; // 推荐等级
}
