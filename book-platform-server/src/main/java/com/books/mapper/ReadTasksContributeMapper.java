package com.books.mapper;

import com.books.entity.Contribute;
import com.books.entity.ReadTaskContribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadTasksContributeMapper {
    List<Contribute> getContributesByReadTaskId(Integer readTaskId);

    void insertRelation(ReadTaskContribute readTaskContribute);

    void deleteByReadTaskId(Integer readTaskId);

    void insertRelationBatch(List<ReadTaskContribute> readTaskContributeList);
}
