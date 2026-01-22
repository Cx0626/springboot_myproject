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
 * 轮播图
 * </p>
 *
 * @author baomidou
 * @since 2025-12-17
 */
@Getter
@Setter
@TableName("carousel")
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 图片
     */
    @TableField("picurl")
    private String picurl;

    /**
     * 时间
     */
    @TableField("addtime")
    private String addtime;
}
