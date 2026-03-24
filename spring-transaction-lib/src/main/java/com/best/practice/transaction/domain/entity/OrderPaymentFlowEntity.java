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
@TableName("tb_payment_flow")
public class OrderPaymentFlowEntity {
    /**
     * 支付流水ID
     */
    @TableId(value = "payment_flow_id",type = IdType.AUTO)
    private Integer paymentFlowId;

    /**
     * 支付流水号
     */
    @TableField(value = "payment_flow_num")
    private String paymentFlowNum;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 三方支付流水号
     */
    @TableField(value = "third_flow_num")
    private String thirdFlowNum;

    /**
     * 实付金额
     */
    @TableField(value = "actual_amount")
    private BigDecimal actualAmount;

    /**
     * 支付方式
     */
    @TableField(value = "payment_type")
    private String paymentType;


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
