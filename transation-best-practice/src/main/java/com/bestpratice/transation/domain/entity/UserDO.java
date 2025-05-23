package com.bestpratice.transation.domain.entity;

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

    @TableId("user_name")
    private String userName;

    @TableId("user_age")
    private Integer userAge;

    @TableId("user_sex")
    private String userSex;

}
