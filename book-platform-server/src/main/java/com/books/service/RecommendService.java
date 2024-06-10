package com.books.service;

import com.books.entity.Recommend;
import org.springframework.stereotype.Service;

@Service
public interface RecommendService {
    /**
     * 教师设立recomend
     * @param recommend
     */

    void recommendByTeacher(Recommend recommend);

    void update(Recommend recommend);
}
