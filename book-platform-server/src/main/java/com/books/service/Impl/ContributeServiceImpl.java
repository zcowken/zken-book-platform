package com.books.service.Impl;

import com.books.entity.Contribute;
import com.books.mapper.ContributeMapper;
import com.books.service.ContributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributeServiceImpl implements ContributeService {

    @Autowired
    ContributeMapper contributeMapper;

    @Override
    public void submit(Contribute contribute) {
        contributeMapper.submit(contribute);
    }
}
