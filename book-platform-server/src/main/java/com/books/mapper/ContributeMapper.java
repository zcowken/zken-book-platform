package com.books.mapper;


import com.books.entity.Contribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContributeMapper {
    void submit(Contribute contribute);
}
