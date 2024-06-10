package com.books.controller.teacher;


import com.books.dto.SolicitationPageRequestDTO;
import com.books.entity.Solicitation;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.SolicitationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("teacherSolicitationController")
@RequestMapping("/teacher/solicitation")
@Slf4j
public class SolicitationController {
    @Autowired
    private SolicitationService solicitationService;


    /**
     * 发布任务
     * @param solicitation
     */
    @PostMapping("/publish")
    Result<String> publishSolicitation(@RequestBody Solicitation solicitation) {
        log.info("发布solicitation：{}", solicitation);
        solicitationService.publishSolicitation(solicitation);
        return Result.success();
    }

    /**
     * 更新
     * @param solicitation
     * @return
     */
    @PutMapping("/update")
    Result<String> updateSolicitation(@RequestBody Solicitation solicitation) {
        log.info("更新solicitation：{}", solicitation);
        solicitationService.updateSolicitation(solicitation);
        return Result.success();
    }

    /**
     * 删除
     * @param solicitation
     */
    @DeleteMapping("/delete")
    Result<String> deleteSolicitation(@RequestBody Solicitation solicitation) {
        log.info("删除solicitation：{}", solicitation);
        solicitationService.deleteSolicitation(solicitation);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    Result<PageResult> pageQuery(@RequestBody SolicitationPageRequestDTO solicitationPageRequestDTO) {
        log.info("进行solicitation 的分页查询");
        PageResult pageResult = solicitationService.pageQuery(solicitationPageRequestDTO);
        return Result.success(pageResult);
    }


    @GetMapping("/")
    Result<Solicitation> getSolicitationById(Integer id) {
        log.info("teacher访问了 id = {} 的Solicitation", id);
        Solicitation solicitation = solicitationService.getById(id);
        return Result.success(solicitation);
    }

}
