package com.xuecheng.model.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程基本信息
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("course_base")
public class CourseBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    @ApiModelProperty("机构名称")
    /**
     * 机构名称
     */
    private String companyName;

    @ApiModelProperty("课程名称")
    /**
     * 课程名称
     */
    private String name;

    @ApiModelProperty("适用人群")
    /**
     * 适用人群
     */
    private String users;

    @ApiModelProperty("课程标签")
    /**
     * 课程标签
     */
    private String tags;

    @ApiModelProperty("大分类")
    /**
     * 大分类
     */
    private String mt;

    @ApiModelProperty("小分类")
    /**
     * 小分类
     */
    private String st;

    @ApiModelProperty("课程等级")
    /**
     * 课程等级
     */
    private String grade;

    @ApiModelProperty("教育模式")
    /**
     * 教育模式(common普通，record 录播，live直播等）
     */
    private String teachmode;

    @ApiModelProperty("课程介绍")
    /**
     * 课程介绍
     */
    private String description;

    @ApiModelProperty("课程小图")
    /**
     * 课程图片
     */
    private String pic;

    @ApiModelProperty("创建时间")
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("修改时间")
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime changeDate;

    @ApiModelProperty("创建人")
    /**
     * 创建人
     */
    private String createPeople;

    @ApiModelProperty("更新人")
    /**
     * 更新人
     */
    private String changePeople;

    @ApiModelProperty("审核状态")
    /**
     * 审核状态
     */
    private String auditStatus;

    @ApiModelProperty("课程发布状态")
    /**
     * 课程发布状态 未发布  已发布 下线
     */
    private String status;


}
