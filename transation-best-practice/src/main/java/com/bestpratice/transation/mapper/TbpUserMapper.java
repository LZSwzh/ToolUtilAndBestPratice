package com.bestpratice.transation.mapper;

import com.bestpratice.transation.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TbpUserMapper {
    @Select("SELECT * FROM tbp_user WHERE user_id = #{userId}")
    public UserDO findById(@Param("userId") Integer userId);
}
