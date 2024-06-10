package com.books.mapper;


import com.books.entity.ApprovedContribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovedContributeMapper {
    List<ApprovedContribute> getApprovedContributeById(Integer id);
}
