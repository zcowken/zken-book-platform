package com.books.controller.teacher;


import com.books.dto.ContributePageRequestDTO;
import com.books.entity.Contribute;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.ContributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("teacherContributeController")
@RequestMapping("/teacher/contribute")
@Slf4j
public class ContributeController {

    @Autowired
    ContributeService contributeService;


    /**
     * 分页查询方法
     * @param contributePageRequestDTO
     * @return
     */
    @PostMapping("/page")
    Result<PageResult> pageRequest(@RequestBody ContributePageRequestDTO contributePageRequestDTO) {
        log.info("查询contribute表中的信息,页码{}，pageSize{}", contributePageRequestDTO.getPage(), contributePageRequestDTO.getPageSize());
        PageResult pageResult = contributeService.pageQuery(contributePageRequestDTO);
        return Result.success(pageResult);
    }


    /**
     * 通过稿件id进行获取稿件内容
     * @param contributeId
     * @return
     */
    @PostMapping("/{contributeId}")
    Result<Contribute> getByContributeId(@PathVariable Integer contributeId) {
        log.info("教师查看contributeId = {}的稿件", contributeId);
        Contribute contribute = contributeService.getById(contributeId);
        return Result.success(contribute);
    }

}
