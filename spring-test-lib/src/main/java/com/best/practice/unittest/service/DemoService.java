package com.best.practice.unittest.service;

import com.best.practice.unittest.domain.dto.UserDTO;


public interface DemoService {
    UserDTO findById(Long userId);
}
