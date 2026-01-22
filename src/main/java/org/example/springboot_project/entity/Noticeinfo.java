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
 * @since 2025-12-17
 */
@Getter
@Setter
@TableName("noticeinfo")
public class Noticeinfo implements Serializable {

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
     * 发布时间
     */
    @TableField("addtime")
    private LocalDateTime addtime;

    /**
     * 公告备注
     */
    @TableField("intro")
    private String intro;
}
