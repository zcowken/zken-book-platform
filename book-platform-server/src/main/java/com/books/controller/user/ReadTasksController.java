package com.books.controller.user;

import com.books.dto.ReadTaskDTO;
import com.books.dto.ReadTaskPageRequestDTO;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.ReadTasksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userReadTasksController")
@RequestMapping("/user/read_tasks")
@Slf4j
public class ReadTasksController {

    @Autowired
    ReadTasksService readTasksService;

    /**
     * 分页查询方法
     * @param readTaskPageRequestDTO
     * @return
     */
    @PostMapping("/page")
    Result<PageResult> pageRequest(@RequestBody ReadTaskPageRequestDTO readTaskPageRequestDTO) {
        log.info("查询readTasks表中的信息,页码{}，pageSize{}", readTaskPageRequestDTO.getPage(), readTaskPageRequestDTO.getPageSize());
        PageResult pageResult = readTasksService.pageQuery(readTaskPageRequestDTO);
        return Result.success(pageResult);
    }

}
