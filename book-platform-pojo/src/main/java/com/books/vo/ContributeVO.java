package com.books.vo;

import com.books.entity.ApprovedContribute;
import com.books.entity.Recommend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributeVO {
    private Integer id;
    private String title;
    private String briefIntroduction;
    private String url;
    private String summary;
    private Integer userId;
    private String picture;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    // 审核信息
    private List<ApprovedContribute> ApprovedContributeList;

    private List<Recommend> recommendList;
}
