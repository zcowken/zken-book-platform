package com.books.service;

import com.books.entity.ApprovedContribute;
import org.springframework.stereotype.Service;

@Service
public interface ApprovedContributeServive {


    /**
     * 更新approveContirbute记录
     * @param approvedContribute
     */
    void update(ApprovedContribute approvedContribute);

    void modify(ApprovedContribute approvedContribute);
}
