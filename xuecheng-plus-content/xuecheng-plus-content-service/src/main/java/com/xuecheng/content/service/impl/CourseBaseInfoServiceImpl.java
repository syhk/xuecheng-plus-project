package com.xuecheng.content.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.model.dto.AddCourseDto;
import com.xuecheng.model.dto.CourseBaseInfoDto;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.po.CourseBase;
import com.xuecheng.model.po.CourseCategory;
import com.xuecheng.model.po.CourseMarket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
//        test select interface
        LambdaQueryWrapper<CourseBase> queryChainWrapper = new LambdaQueryWrapper<>();
//        拼接查询条件
//        根据课程名称模糊查询
        queryChainWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
//        根据课程审核状态
        queryChainWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
//        根据课程发布状态
        queryChainWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());

        Page<CourseBase> page = new Page<>(pageParams.getPageNo(),pageParams.getPageSize());
//        分页查询 E page 分页参数， @Param("ew") Wrapper<T> queryWrapper 查询条件
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page,queryChainWrapper);
//        data
        List<CourseBase> items = pageResult.getRecords();
//        总记录数
        long total = pageResult.getTotal();
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items,total,pageParams.getPageNo(),pageParams.getPageSize());
        return courseBasePageResult;
    }

    @Transactional // 增删改方法需要添加事务
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {
//        参数的合法性校验
        if (StringUtils.isBlank(dto.getName())) {
            throw new RuntimeException("课程名称为空");
        }
        if (StringUtils.isBlank(dto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }
        if (StringUtils.isBlank(dto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }
        if (StringUtils.isBlank(dto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }
        if (StringUtils.isBlank(dto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }
        if (StringUtils.isBlank(dto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }
        if (StringUtils.isBlank(dto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }
//        向课程基本信息表 course_base 写入数据
        CourseBase courseBase = new CourseBase();
//        将传入的页面参数装进数据库模型对象中，从原始对象中拿数据向新对象中装
//        利用 hutool 工具类，将原始对象中的数据拷贝到新对象中，只要属性名称一致就可以拷贝
//        原始对象的属性为空，新对象原来有值也会被 null 覆盖
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setCompanyId(companyId); // 这个赋值需要放在后面以免被覆盖
        courseBase.setCreateDate(LocalDateTime.now());
//        审核状态默认为未提交
        courseBase.setAuditStatus("202002");
//        发布状态为未发布
        courseBase.setStatus("203001");
        int insert=  courseBaseMapper.insert(courseBase);
        if(insert <= 0 ){
            throw new RuntimeException("课程基本信息表插入失败");
        }
//        向课程营销表 course_market 写入数据
        CourseMarket courseMarket = new CourseMarket();
//      将页面输入的数据拷贝到   courseMarket 中
        BeanUtils.copyProperties(dto,courseMarket);
        Long id = courseBase.getId();
//        主键的课程 id
        courseMarket.setId(id);
        saveCourseMarket(courseMarket);
//        从数据库查询课程的详细信息，包括两部分，基本信息和营销信息
        return findCourseBaseById(id);
    }

//    查询课程信息
    public CourseBaseInfoDto  findCourseBaseById(Long id) {
//        从课程基本信息表查询
        CourseBase courseBase = courseBaseMapper.selectById(id);
        if (courseBase == null) { // 为空则不用再继续查询了，直接返回
            return null;
        }
//        从课程营销表查询
        CourseMarket courseMarket = courseMarketMapper.selectById(id);
//        组装在一起
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
//       经过两次拷贝把属性全部拷贝到新对象中
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
//        通过courseCategoryMapper 查询分类信息，将分类信息放在 courseBaseInfoDto 中
        CourseCategory courseCategory = courseCategoryMapper.selectById(courseBase.getMt());
        if(courseCategory != null){
            courseBaseInfoDto.setMtName(courseCategory.getName());
        }
//       设置对应的小分类名称
        courseCategory = courseCategoryMapper.selectById(courseBase.getSt());
        if(courseCategory != null){
            courseBaseInfoDto.setStName(courseCategory.getName());
        }

        return courseBaseInfoDto;
    }


//    单独写一个方法保存营销的信息
    public int saveCourseMarket(CourseMarket courseMarket){
//        参数的合法性校验
        String charge =  courseMarket.getCharge();
        if(StringUtils.isEmpty(charge)){
            throw new RuntimeException("收费规则为空");
        }

//        如果课程收费，价格没有填写也需要抛出异常
        if(charge.equals("201001")){
            if(courseMarket.getPrice() == null || courseMarket.getPrice().floatValue() <= 0){
                throw new RuntimeException("课程的价格不能为空并且必须大于 0");
            }
        }
//        从数据库查询营销信息，存在则更新，不存在则添加
        CourseMarket courseMarketNew = courseMarketMapper.selectById(courseMarket.getId());
        int insert = 0;
        if(courseMarketNew == null){
//           插入数据库
           insert= courseMarketMapper.insert(courseMarket);
        }else {
//            更新数据库
//            将传入的对象的属性数据拷贝到查询出来的对象上
            BeanUtils.copyProperties(courseMarket,courseMarketNew);
//            id 被拷贝的空值覆盖了，这里需要重新设置下
            courseMarketNew.setId(courseMarket.getId());
          insert=  courseMarketMapper.updateById(courseMarketNew);
        }
        return insert;
    }
}
