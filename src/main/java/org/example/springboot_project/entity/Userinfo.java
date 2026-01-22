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
 * 用户信息
 * </p>
 *
 * @author baomidou
 * @since 2025-12-17
 */
@Getter
@Setter
@TableName("userinfo")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    @TableField("name")
    private String name;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 头像链接
     */
    @TableField("picurl")
    private String picurl;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户角色
     */
    @TableField("role")
    private String role;

    /**
     * 用户年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 用户性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 个人简介
     */
    @TableField("intro")
    private String intro;
}
