package com.books.mapper;

import com.books.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    void register(Teacher teacher);

    Teacher getTeacherById(Integer id);

    void updateTeacher(Teacher teacher);

    /**
     * 此语句执行成功的要求是返回的序列化对象是唯一的
     * @param teacher
     * @return
     */
    Teacher login(Teacher teacher);

}
