package com.xuecheng.controller;

import com.xuecheng.service.CoursePublishPreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 课程发布 前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
@RequestMapping("coursePublishPre")
public class CoursePublishPreController {

    @Autowired
    private CoursePublishPreService coursePublishPreService;
}
