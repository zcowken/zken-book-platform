package com.books.controller.teacher;

import com.books.dto.ContributePageRequestDTO;
import com.books.dto.ReadTaskPageRequestDTO;
import com.books.entity.ApprovedContribute;
import com.books.mapper.ReadTasksMapper;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.ApprovedContributeServive;
import com.books.service.ReadTasksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("teacherApproveContributeController")
@RequestMapping("/teacher/approve_contribute")
public class ApproveContributeController {
    /**
     * 教师审核稿件
     * @param approvedContribute
     * @return
     */
    @Autowired
    private ApprovedContributeServive approvedContributeServive;

    @PutMapping("/approve")
    Result<String> approve(@RequestBody ApprovedContribute approvedContribute) {
        log.info("教师审核稿件,结果为:{}", approvedContribute);
        approvedContributeServive.update(approvedContribute);
        return Result.success();
    }

    /**
     * 教师修改审核结果
     * @param approvedContribute
     * @return
     */
    @PutMapping("/modify")
    Result<String> modify(@RequestBody ApprovedContribute approvedContribute) {
        log.info("教师修改审核结果为:{}", approvedContribute);
        approvedContributeServive.modify(approvedContribute);
        return Result.success();
    }

}
