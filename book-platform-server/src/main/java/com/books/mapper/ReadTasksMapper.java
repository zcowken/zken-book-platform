package com.books.mapper;

import com.books.dto.ReadTaskPageRequestDTO;
import com.books.entity.ReadTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadTasksMapper {

    /**
     * 插入阅读任务
     * @param readTask
     */
    void insert(ReadTask readTask);

    /**
     * update
     * @param readTask
     */
    void update(ReadTask readTask);

    /**
     * 删除方法，根据id进行删除
     * @param readTask
     */
    void delete(ReadTask readTask);

    List<ReadTask> pageQuery(ReadTaskPageRequestDTO readTaskPageRequestDTO);
}
