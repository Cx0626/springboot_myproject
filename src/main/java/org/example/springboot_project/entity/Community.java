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
 * 社区信息
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Getter
@Setter
@TableName("community")
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 社区编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 社区名称
     */
    @TableField("name")
    private String name;

    /**
     * 社区地址
     */
    @TableField("address")
    private String address;

    /**
     * 社区经理
     */
    @TableField("manager")
    private String manager;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;
}
