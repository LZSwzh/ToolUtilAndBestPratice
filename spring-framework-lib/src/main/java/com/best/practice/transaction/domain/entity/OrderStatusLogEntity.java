package com.best.practice.transaction.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_order_status_log")
public class OrderStatusLogEntity {
    @TableId(value = "status_log_id",type = IdType.AUTO)
    private Integer statusLogId;

    @TableField(value = "order_id")
    private Integer orderId;

    @TableField(value = "from_status")
    private String fromStatus;

    @TableField(value = "to_status")
    private String toStatus;

    @TableField(value = "trigger_type")
    private String triggerType;

    @TableField(value = "acted_by")
    private String actedBy;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;

    @TableField(value = "updated_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDate;

}
