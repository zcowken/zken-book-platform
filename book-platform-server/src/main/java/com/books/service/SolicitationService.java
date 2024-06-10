package com.books.service;


import com.books.dto.SolicitationPageRequestDTO;
import com.books.entity.Solicitation;
import com.books.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface SolicitationService {


    /**
     * 发布任务
     * @param solicitation
     */
    void publishSolicitation(Solicitation solicitation);

    /**
     * 更新
     * @param solicitation
     * @return
     */

    void updateSolicitation(Solicitation solicitation);

    /**
     * 删除
     * @param solicitation
     */
    void deleteSolicitation(Solicitation solicitation);

    PageResult pageQuery(SolicitationPageRequestDTO solicitationPageRequestDTO);

    Solicitation getById(Integer id);
}
