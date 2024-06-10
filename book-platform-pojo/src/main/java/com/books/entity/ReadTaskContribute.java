package com.books.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadTaskContribute {
    Integer id;
    Integer readTasksId;
    Integer contributeId;
}
