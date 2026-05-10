package com.best.practice.transaction.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("tb_order")
public class OrderEntity {
    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单编号
     */
    @TableField(value = "order_num")
    private String orderNum;


    /**
     * 订单总数量
     */
    @TableField(value = "order_qty")
    private Integer orderQty;


    /**
     * 订单总金额
     */
    @TableField(value = "total_amount")
    private BigDecimal totalAmount;

    @TableField(value = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 订单状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 支付时间
     */
    @TableField(value = "payment_date")
    private LocalDateTime paymentDate;

    /**
     * 发货时间
     */
    @TableField(value = "ship_date")
    private LocalDateTime shipDate;

    /**
     * 完成时间
     */
    @TableField(value = "finish_time")
    private LocalDateTime finishTime;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;

    /**
     * 修改人
     */
    @TableField(value = "updated_by")
    private Integer updatedBy;

    /**
     * 修改时间
     */
    @TableField(value = "updated_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDate;

    /**
     * 版本号
     */
    @TableField(value = "version")
    private Integer version;

}
