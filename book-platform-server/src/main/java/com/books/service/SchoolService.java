package com.books.service;

import com.books.dto.SchoolPageRequestDTO;
import com.books.entity.School;
import com.books.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface SchoolService {
    /**
     * 添加学校
     * @param school
     */
    void addSchool(School school);

    void deleteSchool(School school);

    void updateSchool(School school);

    PageResult pageQuery(SchoolPageRequestDTO schoolPageRequest);

    School getSchoolById(Integer id);
}
