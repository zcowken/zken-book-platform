package com.books.service.Impl;

import com.books.context.BaseContext;
import com.books.dto.ReadTaskDTO;
import com.books.dto.ReadTaskPageRequestDTO;
import com.books.entity.Contribute;
import com.books.entity.ReadTask;
import com.books.entity.ReadTaskContribute;
import com.books.mapper.ReadTasksContributeMapper;
import com.books.mapper.ReadTasksMapper;
import com.books.result.PageResult;
import com.books.service.ReadTasksService;
import com.books.vo.ReadTaskVO;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadTasksServiceImpl implements ReadTasksService {


    @Autowired
    private ReadTasksMapper readTasksMapper;

    @Autowired
    private ReadTasksContributeMapper readTasksContributeMapper;


    /**
     * 发布阅读任务
     * @param readTaskDTO
     */
    @Transactional
    @Override
    public void publishReadTask(ReadTaskDTO readTaskDTO) {
        // 添加教师的id
        Integer teacherId = BaseContext.getCurrentId().intValue();
        // 如果不指定老师ID
        if (readTaskDTO.getTeacherId() == null) {
            readTaskDTO.setTeacherId(teacherId);
        }
        // 设置时间
        readTaskDTO.setCreateTime(LocalDateTime.now());

        // 复制属性
        ReadTask readtask = new ReadTask();
        BeanUtils.copyProperties(readTaskDTO, readtask);// 使用BeanUtil的话，所有不同的属性不可以为null
        readTasksMapper.insert(readtask);

        // 添加稿件和任务关系
        List<Contribute> contributeList = readTaskDTO.getContributes();
        List<ReadTaskContribute> readTaskContributeList = new ArrayList<>();
        if (contributeList != null) {
            for (int i = 0; i < contributeList.size(); i++) {
                ReadTaskContribute readTaskContribute = new ReadTaskContribute();
                readTaskContribute.setContributeId(contributeList.get(i).getId());
                readTaskContribute.setReadTasksId(readtask.getId()); // 使用了generateKey
                // 添加
                readTaskContributeList.add(readTaskContribute);
            }
            // 插入
            readTasksContributeMapper.insertRelationBatch(readTaskContributeList);
        }


    }

    @Transactional
    @Override
    public void modifyReadTask(ReadTaskDTO readTaskDTO) {

        ReadTask readtask = new ReadTask();
        BeanUtils.copyProperties(readTaskDTO, readtask);
        // 删除之前的readTaskId和contribute的关系
        readTasksContributeMapper.deleteByReadTaskId(readtask.getId());

        // 插入新的关系
        // 添加稿件和任务关系
        List<Contribute> contributeList = readTaskDTO.getContributes();
        List<ReadTaskContribute> readTaskContributeList = new ArrayList<>();
        if (contributeList != null) {
            for (int i = 0; i < contributeList.size(); i++) {
                ReadTaskContribute readTaskContribute = new ReadTaskContribute();
                readTaskContribute.setContributeId(contributeList.get(i).getId());
                readTaskContribute.setReadTasksId(readtask.getId()); // 使用了generateKey
                // 添加
                readTaskContributeList.add(readTaskContribute);
            }
            // 插入
            readTasksContributeMapper.insertRelationBatch(readTaskContributeList);
        }


        // 更新task内容
        readTasksMapper.update(readtask);
    }

    @Transactional
    @Override
    public void deleteReadTask(ReadTaskDTO readTaskDTO) {

        ReadTask readtask = new ReadTask();
        BeanUtils.copyProperties(readTaskDTO, readtask);
        // 删除之前的readTaskId和contribute的关系
        readTasksContributeMapper.deleteByReadTaskId(readtask.getId());
        // 删除本体
        readTasksMapper.delete(readtask);
    }

    @Override
    public PageResult pageQuery(ReadTaskPageRequestDTO readTaskPageRequestDTO) {
        PageHelper.startPage(readTaskPageRequestDTO.getPage(), readTaskPageRequestDTO.getPageSize());

        // 获取任务列表
        List<ReadTask> readTasksPage = readTasksMapper.pageQuery(readTaskPageRequestDTO);
        List<ReadTaskVO> readTaskVOList = new ArrayList<>();

        // 补充contribute的信息
        for (int i = 0; i < readTasksPage.size(); i++) {
            ReadTaskVO readTaskVO = new ReadTaskVO();
            BeanUtils.copyProperties(readTasksPage.get(i), readTaskVO);
            // 添加相关的contributes的信息
            List<Contribute> contributeList = readTasksContributeMapper.getContributesByReadTaskId(readTaskVO.getId());
            readTaskVO.setContributes(contributeList);

            // 加入新的list
            readTaskVOList.add(readTaskVO);
        }

        PageResult pageResult = new PageResult(readTaskVOList.size(), readTaskVOList);
        return pageResult;
    }
}
