package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.model.dto.AddCourseDto;
import com.xuecheng.model.dto.CourseBaseInfoDto;
import com.xuecheng.model.dto.EditCourseDto;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private CourseBaseInfoService courseBaseInfoService;
    @ApiOperation("课程分页查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required=false) QueryCourseParamsDto queryCourseParamsDto) {
        return courseBaseInfoService.queryCourseBaseList(pageParams,queryCourseParamsDto);
    }

    //机构id，由于认证系统没有上线暂时硬编码
    Long companyId = 1232141425L;

    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(
            @RequestBody @Validated(value = ValidationGroups.Inster.class)/*不同的接口激活不同的组*/
                                      AddCourseDto addCourseDto){
//        @Validated 注解表示对 addCourseDto 对象进行校验，在对象属性上添加校验注解

        return courseBaseInfoService.createCourseBase(companyId, addCourseDto);
    }



    @ApiOperation("根据课程 id 查询接口")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable  Long courseId){
        return courseBaseInfoService.getCourseBaseById(courseId);
    }



    @ApiOperation("根据课程 id 修改课程信息")
    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated(ValidationGroups.Update.class) EditCourseDto editCourseDto){
        return courseBaseInfoService.modifyCourseBase(companyId,editCourseDto);
    }








}











