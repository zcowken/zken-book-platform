package com.books.mapper;

import com.books.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
     List<Recommend> getRecommendsById(Integer id);


    /**
     * 插入recomend方法
     * @param recommend
     */
    void insert(Recommend recommend);

    void update(Recommend recommend);
}
