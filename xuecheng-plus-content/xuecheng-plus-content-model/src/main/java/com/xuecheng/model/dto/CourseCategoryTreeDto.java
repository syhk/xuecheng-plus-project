package com.xuecheng.model.dto;

import com.xuecheng.model.po.CourseCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true) // 生成equals(Object other) 和 hashCode()方法
@Data
@Slf4j
public class CourseCategoryTreeDto  extends CourseCategory implements Serializable {

    List<CourseCategoryTreeDto> childrenTreeNodes;
}
