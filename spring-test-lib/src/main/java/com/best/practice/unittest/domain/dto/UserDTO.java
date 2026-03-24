package com.best.practice.unittest.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String userPassword;
    private String userEmail;
}
