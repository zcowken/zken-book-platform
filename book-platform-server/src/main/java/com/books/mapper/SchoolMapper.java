package com.books.mapper;

import com.books.dto.SchoolPageRequestDTO;
import com.books.entity.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolMapper {
    /**
     * 添加学校
     * @param school
     */
    void addSchool(School school);

    void deleteSchool(School school);

    void updateSchool(School school);

    /**
     * 学校分页访问
     * @param schoolPageRequest
     * @return
     */
    List<School> pageQuery(SchoolPageRequestDTO schoolPageRequest);

    School getById(Integer id);
}
