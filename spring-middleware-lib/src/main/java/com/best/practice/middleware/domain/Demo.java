package com.best.practice.middleware.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@TableName("tb_demo")
@AllArgsConstructor
@NoArgsConstructor
public class Demo {
    private Integer demoId;
    private String demoName;
    private String demoDesc;
    private LocalDateTime createdTime;
    private Integer createdBy;
    private LocalDateTime updatedTime;
    private Integer updatedBy;
}
