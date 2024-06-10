package com.books.mapper;

import com.books.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
     List<Recommend> getRecommendsById(Integer id);
}
