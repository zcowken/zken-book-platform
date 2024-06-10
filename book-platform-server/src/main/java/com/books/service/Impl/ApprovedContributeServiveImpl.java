package com.books.service.Impl;

import com.books.constant.ApprovedReviewInfoConstant;
import com.books.context.BaseContext;
import com.books.entity.ApprovedContribute;
import com.books.entity.Teacher;
import com.books.mapper.ApprovedContributeMapper;
import com.books.service.ApprovedContributeServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApprovedContributeServiveImpl implements ApprovedContributeServive {
    @Autowired
    ApprovedContributeMapper approvedContributeMapper;

    /**
     * 更新approveContirbute记录
     * @param approvedContribute
     */
    @Transactional
    @Override
    public void update(ApprovedContribute approvedContribute) {
        // 设置教师id
        Integer teacherId = BaseContext.getCurrentId().intValue();
        approvedContribute.setTeacherId(teacherId);
        // 设置审核时间
        approvedContribute.setReviewTime(LocalDateTime.now());

        // 设置审核信息INFO -- 设置为HAVE_BEEN_APPROVED：已经处理结束
        approvedContribute.setReviewInfo(ApprovedReviewInfoConstant.HAVE_BEEN_APPROVED);
        approvedContributeMapper.update(approvedContribute);
    }

    @Override
    public void modify(ApprovedContribute approvedContribute) {

        // modify不需要额外的添加参数
        approvedContributeMapper.update(approvedContribute);
    }
}
