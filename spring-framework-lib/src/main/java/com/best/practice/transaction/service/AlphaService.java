package com.best.practice.transaction.service;

import com.best.practice.transaction.domain.dto.UserDTO;

public interface AlphaService {
    UserDTO findById(Integer id);
}
