package com.books.controller.teacher;

import com.books.entity.Recommend;
import com.books.result.Result;
import com.books.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("teacherRecommendController")
@RequestMapping("/teacher/recommend")
@Slf4j
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    /**
     * 教师推荐
     * @return
     */
    @PostMapping("")
    public Result<String> recommend(@RequestBody Recommend recommend) {
        log.info("教师推荐了：{}", recommend);
        recommendService.recommendByTeacher(recommend);
        return Result.success();
    }


    @PutMapping
    public Result<String> recommendUpdate(@RequestBody Recommend recommend) {
        log.info("教师更新了：{}", recommend);
        recommendService.update(recommend);
        return Result.success();
    }

}
