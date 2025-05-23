package com.bestpratice.transation.controller;


import com.bestpratice.transation.domain.dto.UserDTO;
import com.bestpratice.transation.domain.vo.UserVO;
import com.bestpratice.transation.mapper.TbpUserMapper;
import com.bestpratice.transation.service.AlphaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AlphaController {

    private final AlphaService alphaService;

    private final ModelMapper modelMapper;
    /**
     * 测试接口
     */
    @RequestMapping(value = "/alpha/find/{id}",method = RequestMethod.POST)
    public UserVO find(@PathVariable("id") Integer id){
        log.info("AlphaController find   参数:{}",id);
        UserDTO userDTO = alphaService.findById(id);
        return modelMapper.map(userDTO, UserVO.class);
    }
}
