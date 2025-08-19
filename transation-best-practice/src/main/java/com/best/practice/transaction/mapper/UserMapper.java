package com.best.practice.transaction.mapper;

import com.best.practice.transaction.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM tbp_user WHERE user_id = #{userId}")
    public UserEntity findById(@Param("userId") Integer userId);
}
