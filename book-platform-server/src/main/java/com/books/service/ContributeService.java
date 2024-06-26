package com.books.service;

import com.books.dto.ContributePageRequestDTO;
import com.books.entity.Contribute;
import com.books.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface ContributeService {

    void submit(Contribute contribute);

    void delete(Contribute contribute);

    PageResult pageQuery(ContributePageRequestDTO contributePageRequestDTO);

    /**
     * 更新稿件内容
     * @param contribute
     */
    void update(Contribute contribute);

    Contribute getById(Integer id);
}
