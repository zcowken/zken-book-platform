package com.books.mapper;


import com.books.annotation.AutoFill;
import com.books.dto.ContributePageRequestDTO;
import com.books.entity.Contribute;
import com.books.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContributeMapper {

    void submit(Contribute contribute);

    void deleteById(Integer id);

    List<Contribute> pageQuery(ContributePageRequestDTO contributePageRequestDTO);

    /**
     * 更新稿件内容
     * @param contribute
     */
    void update(Contribute contribute);
}
