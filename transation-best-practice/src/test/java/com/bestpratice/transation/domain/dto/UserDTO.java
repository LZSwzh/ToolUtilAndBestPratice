package com.bestpratice.transation.domain.dto;


import com.bestpratice.transation.domain.base.BaseDTO;
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
