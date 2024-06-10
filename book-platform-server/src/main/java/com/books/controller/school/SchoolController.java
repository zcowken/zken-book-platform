package com.books.controller.school;


import com.books.dto.SchoolPageRequestDTO;
import com.books.entity.School;
import com.books.result.PageResult;
import com.books.result.Result;
import com.books.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/school")
@Slf4j
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 添加学校
     * @param school
     */
    @PostMapping("")
    Result<String> addSchool(@RequestBody School school) {
        log.info("添加school:", school);
        schoolService.addSchool(school);
        return Result.success();
    }


    @DeleteMapping("")
    Result<String> deleteSchool(@RequestBody School school) {
        log.info("删除school:", school);
        schoolService.deleteSchool(school);
        return Result.success();
    }

    @PutMapping("")
    Result<String> updateSchool(@RequestBody School school) {
        log.info("更新school:", school);
        schoolService.updateSchool(school);
        return Result.success();
    }


    @GetMapping("")
    Result<School> getSchool(Integer id) {
        log.info("查询了id = {} 的学校", id);
        School school = schoolService.getSchoolById(id);
        return Result.success(school);
    }


    /**
     * 学校分页访问
     * @param schoolPageRequest
     * @return
     */
    @PostMapping("/page")
    Result<PageResult> pageQuery(@RequestBody SchoolPageRequestDTO schoolPageRequest) {
        PageResult pageResult = schoolService.pageQuery(schoolPageRequest);
        return Result.success(pageResult);
    }


}
