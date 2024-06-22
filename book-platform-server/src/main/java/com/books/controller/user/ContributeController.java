package com.books.controller.user;


import com.books.dto.ContributePageRequestDTO;
import com.books.entity.ApprovedContribute;
import com.books.entity.Contribute;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.ContributeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/submit")
    Result<String> submit(@RequestBody Contribute contribute) {
        log.info("用户上传稿件：{}", contribute);
        contributeService.submit(contribute);
        return Result.success();
    }

    /**
     * 删除稿件
     * @param contribute
     * @return
     */
    @DeleteMapping("/quit")
    Result<String> delete(@RequestBody Contribute contribute) {
        log.info("用户删除稿件：{}", contribute);
        contributeService.delete(contribute);
        return Result.success();
    }

    // 接口没用
//    @PostMapping("/deltail/{id}")
//    Result<Contribute>


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
     * 更新稿件内容
     * @param contribute
     */
    @PutMapping("")
    Result<String> update(@RequestBody Contribute contribute) {
        log.info("用户更新稿件：{}", contribute);
        contributeService.update(contribute);
        return Result.success();
    }


    /**
     * 通过稿件id进行获取稿件内容
     * @param contributeId
     * @return
     */
    @PostMapping("/{contributeId}")
    Result<Contribute> getByContributeId(@PathVariable Integer contributeId) {
        log.info("学生查看contributeId = {}的稿件", contributeId);
        Contribute contribute = contributeService.getById(contributeId);
        return Result.success(contribute);
    }


}
