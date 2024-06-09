package com.books.service.Impl;

import com.books.entity.School;
import com.books.mapper.SchoolMapper;
import com.books.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;


    /**
     * 添加学校
     * @param school
     */
    @Override
    public void addSchool(School school) {
        schoolMapper.addSchool(school);
    }

    @Override
    public void deleteSchool(School school) {
        schoolMapper.deleteSchool(school);
    }

    @Override
    public void updateSchool(School school) {
        schoolMapper.updateSchool(school);

    }
}
