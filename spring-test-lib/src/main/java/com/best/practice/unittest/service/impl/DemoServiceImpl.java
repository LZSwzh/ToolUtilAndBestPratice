package com.best.practice.unittest.service.impl;

import com.best.practice.unittest.domain.dto.UserDTO;
import com.best.practice.unittest.domain.entities.UserEntity;
import com.best.practice.unittest.mapper.DemoMapper;
import com.best.practice.unittest.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

    private final DemoMapper demoMapper;

    private final ModelMapper modelMapper;

    @Override
    public UserDTO findById(Long userId) {
        UserEntity userEntity = demoMapper.selectById(userId);
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
