package com.best.practice.middleware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.best.practice.middleware.domain.Demo;
import com.best.practice.middleware.mapper.DemoMapper;
import com.best.practice.middleware.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
