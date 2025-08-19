package com.best.practice.transaction.domain.dto;


import com.best.practice.transaction.domain.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends BaseDTO {
    private Integer userId;

    private String userName;

    private Integer userAge;

    private String userSex;

    private UserCertDTO certificates;
}
