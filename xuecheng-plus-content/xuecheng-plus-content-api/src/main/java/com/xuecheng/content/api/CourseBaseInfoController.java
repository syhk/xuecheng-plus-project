package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2023/2/11 15:44
 */
@Api(value = "课程信息管理接口",tags = "课程信息管理接口")
@RestController
/**
 * @Controller 标记此类是一个控制器，可以返回视图解析器指定的 html 页面，通过 @ResponseBody 可以将结果返回 json、xml 数据
 * @RestController 是 @ResponseBody 和 @Controller 的结合体，实现 rest 接口开发，返回 json 数据，不能返回 html 页面
 */
public class CourseBaseInfoController {
    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required=false) QueryCourseParamsDto queryCourseParamsDto) {
        CourseBase courseBase = new CourseBase();
        courseBase.setName("测试名称");
        courseBase.setCreateDate(LocalDateTime.now());
        List<CourseBase> courseBases = new ArrayList();
        courseBases.add(courseBase);
        PageResult pageResult = new PageResult<CourseBase>(courseBases,10,1,10);
        return pageResult;
    }
}
