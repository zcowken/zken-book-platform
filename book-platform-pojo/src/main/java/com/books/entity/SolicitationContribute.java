package com.books.entity;


import com.books.entity.Contribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitationContribute {
    private Integer solicitationId; // 参与的Id
    private Integer contributeId;// 投递的稿件id-- 需要是投递之后的稿件才可能投递

}
