package com.bestpratice.transation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bestpratice.transation.domain.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@TableName(value = "tbp_user")
@EqualsAndHashCode(callSuper = false)
public class UserDO extends BaseDO {
    @TableId("user_id")
    private Integer userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_age")
    private Integer userAge;

    @TableField("user_sex")
    private String userSex;

}
