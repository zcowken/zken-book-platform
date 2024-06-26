package com.books.service.Impl;

import com.books.annotation.AutoFill;
import com.books.constant.ApprovedReviewInfoConstant;
import com.books.context.BaseContext;
import com.books.dto.ContributePageRequestDTO;
import com.books.entity.ApprovedContribute;
import com.books.entity.Contribute;
import com.books.entity.Recommend;
import com.books.enumeration.OperationType;
import com.books.mapper.ApprovedContributeMapper;
import com.books.mapper.ContributeMapper;
import com.books.mapper.RecommendMapper;
import com.books.result.PageResult;
import com.books.service.ContributeService;
import com.books.vo.ContributeVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContributeServiceImpl implements ContributeService {

    @Autowired
    ContributeMapper contributeMapper;

    @Autowired
    ApprovedContributeMapper approvedContributeMapper;

    @Autowired
    RecommendMapper recommendMapper;

    @Override
    public void submit(Contribute contribute) {
        // 设置时间
        contribute.setCreateTime(LocalDateTime.now());
        contribute.setUpdateTime(LocalDateTime.now());

        // 设置 创作人 为自己
        contribute.setUserId(BaseContext.getCurrentId().intValue());
        contributeMapper.submit(contribute);


        // 添加到待审核列表中
        ApprovedContribute approvedContribute = new ApprovedContribute();
        approvedContribute.setContributeId(contribute.getId());
        // 设置状态为待审核
        approvedContribute.setReviewInfo(ApprovedReviewInfoConstant.WAIT_TO_APPROVE);
        // 添加到审核列表
        approvedContributeMapper.addSubmitContribute(approvedContribute);
    }

    @Override
    public void delete(Contribute contribute) {
        contributeMapper.deleteById(contribute.getId());
        // 不要 删除相关审核列表的记录
        ApprovedContribute approvedContribute = new ApprovedContribute();
        approvedContribute.setContributeId(contribute.getId());
        approvedContribute.setReviewInfo(ApprovedReviewInfoConstant.HAVE_QUIT);
//        approvedContributeMapper.deleteByContributeId(approvedContribute.getContributeId());
        // 更新状态 - 更新到reviewInfo = HAVE_QUIT
        approvedContributeMapper.updateApprovedContributeByInnerContributeId(approvedContribute);
    }

    @Override
    public PageResult pageQuery(ContributePageRequestDTO contributePageRequestDTO) {
        // 对访问的判断
        if (contributePageRequestDTO.getUserId() != null) {
            contributePageRequestDTO.setUserId(BaseContext.getCurrentId().intValue());
        }


        PageHelper.startPage(contributePageRequestDTO.getPage(), contributePageRequestDTO.getPageSize());
        List<Contribute> contributePage = contributeMapper.pageQuery(contributePageRequestDTO);
        Page<Contribute> p = (Page<Contribute>) contributePage;
        List<ContributeVO> contributeVOList = new ArrayList<>();
        // 补充获取审核和推荐信息
        for (int i = 0; i < contributePage.size(); i++) {
            ContributeVO contributeVO = new ContributeVO();
            // 复制信息
            BeanUtils.copyProperties(contributePage.get(i), contributeVO);
            // 获得推荐和审核信息
            List<ApprovedContribute> approvedContributeList = approvedContributeMapper.getApprovedContributeById(contributeVO.getId());
            List<Recommend> recommendList = recommendMapper.getRecommendsById(contributeVO.getId());
            contributeVO.setApprovedContributeList(approvedContributeList);
            contributeVO.setRecommendList(recommendList);

            // 添加
            contributeVOList.add(contributeVO);
        }
        PageResult pageResult = new PageResult(p.getTotal(), contributeVOList);
        return pageResult;
    }

    /**
     * 更新稿件内容
     * @param contribute
     */
    @Transactional
    @Override
    public void update(Contribute contribute) {
        contribute.setUpdateTime(LocalDateTime.now());
        contributeMapper.update(contribute);
    }

    @Override
    public Contribute getById(Integer id) {
        return contributeMapper.getById(id);
    }
}
