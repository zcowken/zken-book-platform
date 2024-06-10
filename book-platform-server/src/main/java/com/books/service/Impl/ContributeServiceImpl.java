package com.books.service.Impl;

import com.books.annotation.AutoFill;
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
        contributeMapper.submit(contribute);
    }

    @Override
    public void delete(Contribute contribute) {
        contributeMapper.deleteById(contribute.getId());
    }

    @Override
    public PageResult pageQuery(ContributePageRequestDTO contributePageRequestDTO) {
        PageHelper.startPage(contributePageRequestDTO.getPage(), contributePageRequestDTO.getPageSize());
        List<Contribute> contributePage = contributeMapper.pageQuery(contributePageRequestDTO);
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

        PageResult pageResult = new PageResult(contributeVOList.size(), contributeVOList);
        return pageResult;
    }
}
