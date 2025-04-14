package com.best.practice.unittest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.best.practice.unittest.domain.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<UserEntity> {
}
