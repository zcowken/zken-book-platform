package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitation {

    private Integer id;
    private Integer teacherId;
    private String coverUrl;
    private String title;
    private String Integerroduction;
    private String requirements;
    private String solicitationContent;
}
