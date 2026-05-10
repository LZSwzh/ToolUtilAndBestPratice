package com.best.practice.transaction.domain.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单状态日志")
public class OrderStatusLogVO {

    @ApiModelProperty(value = "订单状态日志id")
    private Integer statusLogId;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "原始状态")
    private String fromStatus;

    @ApiModelProperty(value = "更新状态")
    private String toStatus;

    @ApiModelProperty(value = "触发方式")
    private String triggerType;

    @ApiModelProperty(value = "操作人")
    private String actedBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDate;

}
