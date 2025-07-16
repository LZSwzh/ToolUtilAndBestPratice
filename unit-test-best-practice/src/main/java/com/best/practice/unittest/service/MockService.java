package com.best.practice.unittest.service;

import com.best.practice.unittest.domain.dto.UserDTO;

public interface MockService {
    UserDTO findById(Long userId);
}
