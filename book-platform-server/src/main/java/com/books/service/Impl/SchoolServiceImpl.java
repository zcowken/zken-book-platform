package com.books.service.Impl;

import com.books.dto.SchoolPageRequestDTO;
import com.books.entity.School;
import com.books.mapper.SchoolMapper;
import com.books.result.PageResult;
import com.books.service.SchoolService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 学校分页访问
     * @param schoolPageRequest
     * @return
     */
    @Override
    public PageResult pageQuery(SchoolPageRequestDTO schoolPageRequest) {
        PageHelper.startPage(schoolPageRequest.getPage(), schoolPageRequest.getPageSize());
        List<School> schoolList = schoolMapper.pageQuery(schoolPageRequest);
        PageResult pageResult = new PageResult(schoolList.size(), schoolList);
        return pageResult;
    }

    @Override
    public School getSchoolById(Integer id) {
        return schoolMapper.getById(id);
    }
}
