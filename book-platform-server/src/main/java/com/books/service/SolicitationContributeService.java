package com.books.service;

import com.books.entity.SolicitationContribute;
import org.springframework.stereotype.Service;

@Service
public interface SolicitationContributeService {
    void submitByUser(SolicitationContribute solicitationContribute);

    void quitByUser(SolicitationContribute solicitationContribute);

    void updateByUser(SolicitationContribute solicitationContribute);
}
