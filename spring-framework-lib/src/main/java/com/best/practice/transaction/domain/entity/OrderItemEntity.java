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
@TableName("tb_order_item")
public class OrderItemEntity {

    /**
     * 订单项ID
     */
    @TableId(value = "order_item_id",type = IdType.AUTO)
    private Integer orderItemId;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 商品ID
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 购买数量
     */
    @TableField(value = "item_qty")
    private Integer itemQty;

    /**
     * SKU ID
     */
    @TableField(value = "sku_id")
    private Integer skuId;

    /**
     * SKU 名称
     */
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * SKU 单价
     */
    @TableField(value = "sku_unit_price")
    private BigDecimal skuUnitPrice;

    /**
     * 折扣
     */
    @TableField(value = "discount")
    private BigDecimal discount;

    /**
     * 折后金额
     */
    @TableField(value = "actual_price")
    private BigDecimal actualPrice;

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
