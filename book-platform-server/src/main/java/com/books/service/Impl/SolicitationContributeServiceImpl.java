package com.books.service.Impl;

import com.books.entity.SolicitationContribute;
import com.books.mapper.SolicitationContributeMapper;
import com.books.service.SolicitationContributeService;
import com.books.service.SolicitationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitationContributeServiceImpl implements SolicitationContributeService {

    @Autowired
    private SolicitationContributeMapper solicitationContributeMapper;

    /**
     * 用户参与征稿
     * @param solicitationContribute
     */
    @Override
    public void submitByUser(SolicitationContribute solicitationContribute) {
        solicitationContributeMapper.insert(solicitationContribute);
    }

    /**
     * 取消投稿
     * @param solicitationContribute
     */
    @Override
    public void quitByUser(SolicitationContribute solicitationContribute) {
        solicitationContributeMapper.delete(solicitationContribute);
    }


    /**
     * 更新参与征稿
     * @param solicitationContribute
     */
    @Override
    public void updateByUser(SolicitationContribute solicitationContribute) {
        solicitationContributeMapper.update(solicitationContribute);
    }
}
