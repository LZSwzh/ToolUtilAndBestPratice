package com.best.practice.unittest.controller;

import com.best.practice.unittest.domain.dto.UserDTO;
import com.best.practice.unittest.domain.vo.UserVO;
import com.best.practice.unittest.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

=======
@Slf4j
>>>>>>> 8a64635ec3efedab010d4dba7f394b5574960990
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

    public static void main(String[] args) {
        String input = "confirm";
        try {
            // 获取 SHA - 256 消息摘要实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 计算输入字符串的哈希值
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            System.out.println("SHA-256 hash of \"" + input + "\": " + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
