package org.example.springboot_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 房间信息
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Getter
@Setter
@TableName("room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房间号
     */
    @TableField("room_num")
    private String room_num;

    /**
     * 房间面积
     */
    @TableField("area")
    private BigDecimal area;

    /**
     * 所属楼栋
     */
    @TableField("building_id")
    private Integer building_id;

    /**
     * 是否入住（true=已入住，false=未入住）
     */
    @TableField("is_check_in")
    private Boolean is_check_in;
}