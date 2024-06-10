package com.books.service.Impl;

import com.books.context.BaseContext;
import com.books.dto.SolicitationPageRequestDTO;
import com.books.entity.Solicitation;
import com.books.mapper.SolicitationMapper;
import com.books.result.PageResult;
import com.books.service.SolicitationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitationServiceImpl implements SolicitationService {
    @Autowired
    SolicitationMapper solicitationMapper;

    /**
     * 发布任务
     * @param solicitation
     */
    @Transactional
    @Override
    public void publishSolicitation(Solicitation solicitation) {
        if (solicitation.getTeacherId() == null) {
            Integer teacherId = BaseContext.getCurrentId().intValue();
            solicitation.setTeacherId(teacherId);
        }
        solicitationMapper.insert(solicitation);
    }

    /**
     * 更新
     * @param solicitation
     * @return
     */

    @Transactional
    @Override
    public void updateSolicitation(Solicitation solicitation) {
        solicitationMapper.update(solicitation);
    }

    /**
     * 删除
     * @param solicitation
     */
    @Transactional
    @Override
    public void deleteSolicitation(Solicitation solicitation) {
        solicitationMapper.delete(solicitation);
    }

    /**
     * 分页查询
     * @param solicitationPageRequestDTO
     */
    @Override
    public PageResult pageQuery(SolicitationPageRequestDTO solicitationPageRequestDTO) {
        PageHelper.startPage(solicitationPageRequestDTO.getPage(), solicitationPageRequestDTO.getPageSize());
        // 获取列表
        List<Solicitation> solicitationsPage = solicitationMapper.pageQuery(solicitationPageRequestDTO);
        PageResult pageResult = new PageResult(solicitationsPage.size(), solicitationsPage);
        return pageResult;
    }

    @Override
    public Solicitation getById(Integer id) {
        return solicitationMapper.getById(id);
    }
}
