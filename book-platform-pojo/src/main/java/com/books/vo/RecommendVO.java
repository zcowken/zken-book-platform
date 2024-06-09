package com.books.vo;

import com.books.entity.Contribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendVO {
    Integer id;
    Integer teacherId;
    Integer contributeId;
    String reason;
    Integer recommendLevel; // 推荐等级

    Contribute contribute; // 推荐的稿件
}
