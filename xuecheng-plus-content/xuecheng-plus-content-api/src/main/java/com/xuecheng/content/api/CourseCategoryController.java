package com.xuecheng.content.api;


import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.model.dto.CourseCategoryTreeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  课程分类相关接口
 */

@Slf4j
@RestController
public class CourseCategoryController {
    @Autowired
    private CourseCategoryService courseCategoryService;
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
    return courseCategoryService.queryTreeNodes("1");
    }


}
