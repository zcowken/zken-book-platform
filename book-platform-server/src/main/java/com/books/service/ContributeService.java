package com.books.service;

import com.books.entity.Contribute;
import org.springframework.stereotype.Service;

@Service
public interface ContributeService {

    void submit(Contribute contribute);
}
