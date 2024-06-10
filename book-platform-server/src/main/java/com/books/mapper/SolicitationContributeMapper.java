package com.books.mapper;

import com.books.entity.SolicitationContribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SolicitationContributeMapper {

    /**
     * 插入
     * @param solicitationContribute
     */
    void insert(SolicitationContribute solicitationContribute);

    /**
     * 删除
     * @param solicitationContribute
     */
    void delete(SolicitationContribute solicitationContribute);

    /**
     * 更新
     * @param solicitationContribute
     */
    void update(SolicitationContribute solicitationContribute);
}
