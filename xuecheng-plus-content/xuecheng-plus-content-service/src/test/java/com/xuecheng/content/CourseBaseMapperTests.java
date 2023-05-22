package com.xuecheng.content;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.po.CourseBase;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseBaseMapperTests {
    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Test
    public void testCourseBaseMapper(){
        CourseBase courseBase  = courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);

//        test select interface
        LambdaQueryWrapper<CourseBase> queryChainWrapper = new LambdaQueryWrapper<>();
//        select conditions
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");
        queryCourseParamsDto.setAuditStatus("202004");
        queryCourseParamsDto.setPublishStatus("203001");

//        拼接查询条件
//        根据课程名称模糊查询
        queryChainWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());

//        根据课程审核状态
        queryChainWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());

//        分页参数
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(3L);
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(),pageParams.getPageSize());

//        分页查询 E page 分页参数， @Param("ew") Wrapper<T> queryWrapper 查询条件
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page,queryChainWrapper);

//        data
        List<CourseBase> items = pageResult.getRecords();

//        总记录数
        long total = pageResult.getTotal();
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items,total,pageParams.getPageNo(),pageParams.getPageSize());
        System.out.println(courseBasePageResult);

    }
}
