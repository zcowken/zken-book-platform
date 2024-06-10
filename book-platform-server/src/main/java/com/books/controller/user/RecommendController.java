package com.books.controller.user;

import com.books.entity.Recommend;
import com.books.result.Result;
import com.books.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userRecommendController")
@RequestMapping("/user/recommend")
@Slf4j
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    /**
     * 这个接口目前是空的
     */
}
