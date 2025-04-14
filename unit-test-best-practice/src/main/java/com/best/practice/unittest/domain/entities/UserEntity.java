package com.best.practice.unittest.domain.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class UserEntity {
    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_email")
    private String userEmail;
}
