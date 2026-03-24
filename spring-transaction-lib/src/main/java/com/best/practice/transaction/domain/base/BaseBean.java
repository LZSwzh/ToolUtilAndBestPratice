package com.best.practice.transaction.domain.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 1.为什么要针对MongoDB的时间格式进行自定义转换？
 * Spring Data Mongo默认使用ISO=DateTimeFormatter.ISO_LOCAL_DATE_TIME即yyyy-MM-ddTHH:mm:ss解析时间
 * @JsonFormat 是为了Jackson 的JSON的序列化和反序列化
 * @DateTimeFormat 注解用于Spring框架解析和处理表单提交或者网络请求参数中的日期格式
 * 因此需要自定义转换器将Mongo内部的时间格式转换为LocalDateTime希望的格式
 * 2.为什么要不需要针对Mysql进行自定义转换？
 * 这里使用MybatisPlus操作Mysql，本质上还是对JDBC的封装，JDBC内部会将Mysql的时间格式转换为LocalDateTime
 */
@Data
public class BaseBean {


    private Integer createdBy;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime creationDate;

    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    private Integer updatedBy;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDate;
}
