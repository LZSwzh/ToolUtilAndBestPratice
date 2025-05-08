package com.best.practice.unittest.controller;

import com.best.practice.unittest.domain.dto.UserDTO;
import com.best.practice.unittest.domain.vo.UserVO;
import com.best.practice.unittest.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    private final ModelMapper modelMapper;

    @RequestMapping(value = "/v1/user/{userId}",method = RequestMethod.GET)
    public UserVO getUserById(@PathVariable Long userId){
        log.info("查询用户信息   参数:{}",userId);
        UserDTO userDTO = demoService.findById(userId);
        return modelMapper.map(userDTO, UserVO.class);
    }
}
