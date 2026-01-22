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
 * 报修信息
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Getter
@Setter
@TableName("repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报修编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private Integer user_id;

    /**
     * 房间编号
     */
    @TableField("room_id")
    private Integer room_id;

    /**
     * 报修类型
     */
    @TableField("type")
    private String type;

    /**
     * 处理状态
     */
    @TableField("status")
    private String status;

    /**
     * 报修内容
     */
    @TableField("content")
    private String content;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime create_time;
}
