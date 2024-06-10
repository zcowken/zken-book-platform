package com.books.controller.user;


import com.books.entity.SolicitationContribute;
import com.books.result.Result;
import com.books.service.SolicitationContributeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userSolicitationContributeController")
@RequestMapping("/user/solicitation_contribute")
@Slf4j
public class SolicitationContributeController {

    @Autowired
    private SolicitationContributeService solicitationContributeService;

    /**
     * 用户参与征稿
     * @param solicitationContribute
     * @return
     */
    @PostMapping("")
    Result<String> solicitationContributeSubmit(@RequestBody SolicitationContribute solicitationContribute) {
        log.info("用户参与征稿：{}", solicitationContribute);
        solicitationContributeService.submitByUser(solicitationContribute);
        return Result.success();
    }

    /**
     * 用户取消征稿
     * @param solicitationContribute
     * @return
     */
    @DeleteMapping("")
    Result<String> solicitationContributeQuit(@RequestBody SolicitationContribute solicitationContribute) {
        log.info("用户取消征稿：{}", solicitationContribute);
        solicitationContributeService.quitByUser(solicitationContribute);
        return Result.success();
    }


    /**
     * 用户更新数据
     * @param solicitationContribute
     * @return
     */
    @PutMapping("")
    Result<String> update(@RequestBody SolicitationContribute solicitationContribute) {
        log.info("用户更新数据：{}", solicitationContribute);
        solicitationContributeService.updateByUser(solicitationContribute);
        return Result.success();
    }
}
