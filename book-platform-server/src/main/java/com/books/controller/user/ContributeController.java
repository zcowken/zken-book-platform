package com.books.controller.user;


import com.books.entity.Contribute;
import com.books.result.Result;
import com.books.service.ContributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userContributeController")
@RequestMapping("/user/contribute")
@Slf4j
public class ContributeController {

    @Autowired
    ContributeService contributeService;

    /**
     * 用户上床稿件
     * @param contribute
     * @return
     */
    Result<String> submit(Contribute contribute) {
        log.info("用户上传稿件，", contribute);
        contributeService.submit(contribute);
        return Result.success();
    }



}
