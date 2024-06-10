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
}
