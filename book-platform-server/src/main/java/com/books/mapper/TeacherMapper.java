package com.books.mapper;

import com.books.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    void register(Teacher teacher);

    Teacher getTeacherById(Integer id);

    void updateTeacher(Teacher teacher);

    Teacher login(Teacher teacher);

}
