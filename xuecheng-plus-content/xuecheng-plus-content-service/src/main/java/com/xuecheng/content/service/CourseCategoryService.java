package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.model.dto.CourseCategoryTreeDto;
import com.xuecheng.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-02-11
 */
public interface CourseCategoryService  {

    List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
