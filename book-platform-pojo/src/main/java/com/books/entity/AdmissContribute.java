package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissContribute {
    Integer id;
    Integer contributeId;
    LocalDateTime admissTime;
}
