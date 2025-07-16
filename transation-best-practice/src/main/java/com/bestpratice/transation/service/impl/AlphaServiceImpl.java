package com.bestpratice.transation.service.impl;

import com.bestpratice.transation.domain.dto.UserCertDTO;
import com.bestpratice.transation.domain.dto.UserDTO;
import com.bestpratice.transation.domain.entity.UserCertDO;
import com.bestpratice.transation.domain.entity.UserDO;
import com.bestpratice.transation.mapper.TbpUserCertMapper;
import com.bestpratice.transation.mapper.TbpUserMapper;
import com.bestpratice.transation.service.AlphaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlphaServiceImpl implements AlphaService {

    private final TbpUserMapper tbpUserMapper;

    private final TbpUserCertMapper tbpUserCertMapper;
    private final ModelMapper modelMapper;
    @Override
    public UserDTO findById(Integer id) {
        UserDO userInfo = tbpUserMapper.findById(id);
        UserDTO userDTO = modelMapper.map(userInfo, UserDTO.class);
        //TODO 查询Mongo 测试Mongo是否整合成功
        Optional<UserCertDO> userCertOpt = tbpUserCertMapper.findByUserId(userDTO.getUserId());
        userCertOpt.ifPresent(t-> {
            UserCertDTO userCerts = modelMapper.map(t, UserCertDTO.class);
            userDTO.setCertificates(userCerts);
        });
        return userDTO;
    }
}
