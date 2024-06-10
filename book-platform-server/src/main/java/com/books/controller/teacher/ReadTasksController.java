package com.books.controller.teacher;

import com.books.dto.ReadTaskDTO;
import com.books.dto.ReadTaskPageRequestDTO;
import com.books.entity.ReadTask;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.ReadTasksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("teacherReadTasksController")
@RequestMapping("/teacher/read_tasks")
@Slf4j
public class ReadTasksController {

    @Autowired
    ReadTasksService readTasksService;


    /**
     * 发布阅读任务
     * @param readTaskDTO
     */
    @PostMapping("/publish")
    Result<String> publishReadTask(@RequestBody ReadTaskDTO readTaskDTO) {
        log.info("教师发布阅读任务：{}", readTaskDTO);
        readTasksService.publishReadTask(readTaskDTO);
        return Result.success();
    }

    /**
     * 更新阅读任务
     * @param readTaskDTO
     */
    @PostMapping("/update")
    Result<String> modifyReadTask(@RequestBody ReadTaskDTO readTaskDTO) {
        log.info("教师更新阅读任务：{}", readTaskDTO);
        readTasksService.modifyReadTask(readTaskDTO);
        return Result.success();
    }

    /**
     * 删除task
     * @param readTaskDTO
     */
    @DeleteMapping("/delete")
    Result<String> deleteReadTask(@RequestBody ReadTaskDTO readTaskDTO) {
        log.info("教师更新阅读任务：{}", readTaskDTO);
        readTasksService.deleteReadTask(readTaskDTO);
        return Result.success();
    }

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
