package com.books.mapper;

import com.books.entity.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper {
    /**
     * 添加学校
     * @param school
     */
    void addSchool(School school);

    void deleteSchool(School school);

    void updateSchool(School school);
}
