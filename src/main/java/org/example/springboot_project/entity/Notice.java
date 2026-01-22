package org.example.springboot_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 公告信息
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Getter
@Setter
@TableName("notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告标题
     */
    @TableField("title")
    private String title;

    /**
     * 公告内容
     */
    @TableField("content")
    private String content;

    /**
     * 发布人
     */
    @TableField("author")
    private String author;

    /**
     * 发布时间
     */
    @TableField("publish_time")
    private LocalDateTime publish_time;
}
