package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedContribute {
    private Integer id;
    Integer contributeId;
    Integer teacherId;
    Integer reviewInfo; // 暂时可能没用
    LocalDateTime reviewTime;
    Integer reviewResult;
    String improvementSuggestion;  // 改进建议（无论通过或者未通过都可以有）


}
