package com.books.mapper;


import com.books.entity.ApprovedContribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovedContributeMapper {
    List<ApprovedContribute> getApprovedContributeById(Integer id);

    void addSubmitContribute(ApprovedContribute approvedContribute);

    void deleteByContributeId(Integer contributeId);


    /**
     * 使用对象内的contributeId进行更新
     * 再用户删除投稿的时候会使用
     * @param approvedContribute
     */
    void updateApprovedContributeByInnerContributeId(ApprovedContribute approvedContribute);

    /**
     * 更新一个审核关系（使用id）
     */
    void update(ApprovedContribute approvedContribute);
}
