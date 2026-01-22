package org.example.springboot_project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 缴费信息
 * </p>
 *
 * @author baomidou
 * @since 2026-01-02
 */
@Getter
@Setter
@TableName("payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 缴费编号
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
     * 缴费类型
     */
    @TableField("type")
    private String type;

    /**
     * 缴费金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 缴费状态
     */
    @TableField("status")
    private String status;

    /**
     * 缴费时间
     */
    @TableField("pay_time")
    private LocalDateTime pay_time;
}
