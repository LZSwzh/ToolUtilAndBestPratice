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
@ApiModel(value = "订单项表")
public class OrderItemVO {

    /**
     * 订单项ID
     */
    @ApiModelProperty(value = "订单项ID")
    private Integer orderItemId;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    /**
     * 购买数量
     */
    @ApiModelProperty(value = "购买数量")
    private Integer itemQty;

    /**
     * SKU ID
     */
    @ApiModelProperty(value = "SKU ID")
    private Integer skuId;

    /**
     * SKU 名称
     */
    @ApiModelProperty(value = "SKU 名称")
    private String skuName;

    /**
     * SKU 单价
     */
    @ApiModelProperty(value = "SKU 单价")
    private BigDecimal skuUnitPrice;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    /**
     * 折后金额
     */
    @ApiModelProperty(value = "折后金额")
    private BigDecimal actualPrice;

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
