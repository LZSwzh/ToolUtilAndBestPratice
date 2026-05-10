package com.best.practice.transaction.service.impl;

import com.best.practice.transaction.domain.dto.UserCertDTO;
import com.best.practice.transaction.domain.dto.UserDTO;
import com.best.practice.transaction.domain.entity.UserCertEntity;
import com.best.practice.transaction.domain.entity.UserEntity;
import com.best.practice.transaction.mapper.UserCertMapper;
import com.best.practice.transaction.mapper.UserMapper;
import com.best.practice.transaction.service.AlphaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlphaServiceImpl implements AlphaService {

    private final UserMapper userMapper;

    private final UserCertMapper userCertMapper;
    private final ModelMapper modelMapper;
    @Override
    public UserDTO findById(Integer id) {
        UserEntity userInfo = userMapper.findById(id);
        UserDTO userDTO = modelMapper.map(userInfo, UserDTO.class);
        //TODO 查询Mongo 测试Mongo是否整合成功
        Optional<UserCertEntity> userCertOpt = userCertMapper.findByUserId(userDTO.getUserId());
        userCertOpt.ifPresent(t-> {
            UserCertDTO userCerts = modelMapper.map(t, UserCertDTO.class);
            userDTO.setCertificates(userCerts);
        });
        return userDTO;
    }
}
