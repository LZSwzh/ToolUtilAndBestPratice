package com.best.practice.transaction.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("支付流水")
public class OrderPaymentFlowVO {
    /**
     * 支付流水ID
     */
    @ApiModelProperty(value = "支付流水ID")
    private Integer paymentFlowId;

    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    private String paymentFlowNum;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    /**
     * 三方支付流水号
     */
    @ApiModelProperty(value = "三方支付流水号")
    private String thirdFlowNum;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    private BigDecimal actualAmount;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentType;


    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Integer updatedBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDate;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;

}
