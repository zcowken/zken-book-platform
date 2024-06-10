package com.books.controller.user;


import com.books.dto.ContributePageRequestDTO;
import com.books.entity.Contribute;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.ContributeService;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("")
    Result<String> submit(@RequestBody Contribute contribute) {
        log.info("用户上传稿件：{}", contribute);
        contributeService.submit(contribute);
        return Result.success();
    }


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


}
