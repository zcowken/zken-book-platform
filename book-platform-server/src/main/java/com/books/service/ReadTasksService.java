package com.books.service;

import com.books.dto.ReadTaskDTO;
import com.books.dto.ReadTaskPageRequestDTO;
import com.books.entity.ReadTask;
import com.books.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface ReadTasksService {

    /**
     * 发布阅读任务
     * @param readTask
     */
    void publishReadTask(ReadTaskDTO readTaskDTO);

    void modifyReadTask(ReadTaskDTO readTaskDTO);

    void deleteReadTask(ReadTaskDTO readTaskDTO);

    PageResult pageQuery(ReadTaskPageRequestDTO readTaskPageRequestDTO);
}
