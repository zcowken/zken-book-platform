package com.books.mapper;

import com.books.dto.SolicitationPageRequestDTO;
import com.books.entity.ReadTask;
import com.books.entity.Solicitation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SolicitationMapper {
    void insert(Solicitation solicitation);

    void update(Solicitation solicitation);

    void delete(Solicitation solicitation);

    List<Solicitation> pageQuery(SolicitationPageRequestDTO solicitationPageRequestDTO);

    Solicitation getById(Integer id);
}
