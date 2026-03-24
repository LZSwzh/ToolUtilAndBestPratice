package com.best.practice.unittest.domain.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class UserEntity {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_email")
    private String userEmail;
}
