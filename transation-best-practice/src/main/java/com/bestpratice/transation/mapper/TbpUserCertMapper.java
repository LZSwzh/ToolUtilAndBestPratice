package com.bestpratice.transation.mapper;

import com.bestpratice.transation.domain.entity.UserCertDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbpUserCertMapper extends MongoRepository<UserCertDO,Integer> {
    Optional<UserCertDO> findByUserId(int userId);
}
