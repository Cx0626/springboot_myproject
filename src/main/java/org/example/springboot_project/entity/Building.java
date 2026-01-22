package org.example.springboot_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 楼栋信息
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Getter
@Setter
@TableName("building")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼栋名称
     */
    @TableField("name")
    private String name;

    /**
     * 楼栋层数
     */
    @TableField("floor")
    private Integer floor;

    /**
     * 楼栋单元
     */
    @TableField("unit")
    private Integer unit;

    /**
     * 所属社区
     */
    @TableField("community_id")
    private Integer community_id;
}
