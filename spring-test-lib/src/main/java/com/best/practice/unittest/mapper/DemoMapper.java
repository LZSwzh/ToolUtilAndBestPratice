package com.best.practice.unittest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.best.practice.unittest.domain.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DemoMapper extends BaseMapper<UserEntity> {
}
