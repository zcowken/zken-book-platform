package com.books.service.Impl;

import com.books.context.BaseContext;
import com.books.entity.Recommend;
import com.books.mapper.RecommendMapper;
import com.books.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendMapper recommendMapper;


    /**
     * 教师设立recomend
     * @param recommend
     */
    @Override
    public void recommendByTeacher(Recommend recommend) {
        // 添加教师id
        Integer teacherId = BaseContext.getCurrentId().intValue();
        recommend.setTeacherId(teacherId);
        recommendMapper.insert(recommend);
    }

    @Override
    public void update(Recommend recommend) {
        recommendMapper.update(recommend);
    }
}
