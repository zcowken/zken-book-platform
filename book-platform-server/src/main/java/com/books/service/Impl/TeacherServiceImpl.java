package com.books.service.Impl;

import com.books.entity.Teacher;
import com.books.entity.Teacher;
import com.books.exception.LoginFailedException;
import com.books.mapper.TeacherMapper;
import com.books.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void register(Teacher teacher) {
        // 添加时间
        teacher.setCreateTime(LocalDateTime.now());
        teacherMapper.register(teacher);
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherMapper.getTeacherById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        Teacher teacher1 = new Teacher();
        BeanUtils.copyProperties(teacher, teacher1);
        teacherMapper.updateTeacher(teacher1);
    }

    @Override
    public Teacher login(Teacher teacher) {
        Teacher teacherRet = teacherMapper.login(teacher);
        if (teacherRet == null) {
            throw new LoginFailedException("用户不存在或密码错误");
        }
        return teacherRet;
    }
}
