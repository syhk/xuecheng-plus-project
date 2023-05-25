package com.xuecheng.content;


import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.model.dto.CourseCategoryTreeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseCategoryServiceImpl {


    @Autowired
    CourseCategoryService courseCategoryService;

    @Test
    public void testCourseCategoryServiceImpl(){

        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryService.queryTreeNodes("1");
        System.out.println(courseCategoryTreeDtos);



    }

}
