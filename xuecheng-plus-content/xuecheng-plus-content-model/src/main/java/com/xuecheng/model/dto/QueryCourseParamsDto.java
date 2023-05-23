package com.xuecheng.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程查询条件模型类
 * @date 2023/2/11 15:37
 */

/**
 * DTO (Data Transfer Object) 数据传输对象，用于封装业务数据，进行传输
 * PO (Persistent Object) / BO （Business Object） 持久化对象，用于封装数据库表中的字段，一般与数据库表一一对应
 */

@Data
@ToString
public class QueryCourseParamsDto {
    @ApiModelProperty("审核状态")
    //审核状态
    private String auditStatus;
    @ApiModelProperty("课程名称")
    //课程名称
    private String courseName;
    @ApiModelProperty("发布状态")
    //发布状态
    private String publishStatus;

}
