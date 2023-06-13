package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.model.dto.AddCourseDto;
import com.xuecheng.model.dto.CourseBaseInfoDto;
import com.xuecheng.model.dto.EditCourseDto;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.po.CourseBase;

/**
 * @author syhk
 * @description: 课程基本信息管理接口
 * @create: 2020/3/5
 */
public interface CourseBaseInfoService {


    /**
     * 课程分页查询
     * @param pageParams 分页参数
     * @param queryCourseParamsDto 查询条件
     * @return
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     *  新增课程
     * @param companyId 机构id
     * @param addCourseDto 课程信息
     * @return 课程基本信息
     */
  CourseBaseInfoDto createCourseBase(Long companyId,AddCourseDto addCourseDto);


    /**
     * 根据课程 id 查询课程信息
     * @param courseId
     * @return
     */
 CourseBaseInfoDto getCourseBaseById(Long courseId);


    /**
     * 修改课程信息
     * @param editCourseDto
     * @return
     */
    CourseBaseInfoDto modifyCourseBase(Long companId,EditCourseDto editCourseDto);


    CourseBaseInfoDto getCourseBaseInfo(Long courseId);
}
