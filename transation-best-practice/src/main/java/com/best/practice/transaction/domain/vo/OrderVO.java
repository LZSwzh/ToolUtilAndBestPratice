package com.best.practice.transaction.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "订单主表")
public class OrderVO {
    /**
     * 订单id
     */
    @NotNull
    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNum;


    /**
     * 订单总数量
     */
    @ApiModelProperty(value = "订单总数量")
    private Integer orderQty;


    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal payAmount;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String status;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentDate;

    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    private LocalDateTime shipDate;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;

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

    @ApiModelProperty(value = "订单明细")
    private List<OrderItemVO> orderItemList;
}
