package com.bestpratice.transation.domain.vo;

import com.bestpratice.transation.domain.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends BaseVO {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户年龄
     */
    private Integer userAge;
    /**
     * 用户性别
     */
    private String userSex;
    /**
     * 用户身份证信息
     */
    private List<UserCertVO> certificates;

}
