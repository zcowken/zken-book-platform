package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contribute {
    private Integer id;
    private String title;
    private String briefIntroduction;
    private String url;
    private String summary;
    private Integer userId;
    private String picture;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
