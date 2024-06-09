package com.books.service;

import com.books.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    void register(Teacher teacher);

    Teacher getTeacherById(Integer id);

    void updateTeacher(Teacher teacher);

    Teacher login(Teacher teacher);
}
