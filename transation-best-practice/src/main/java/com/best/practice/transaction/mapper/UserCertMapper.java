package com.best.practice.transaction.mapper;

import com.best.practice.transaction.domain.entity.UserCertEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCertMapper extends MongoRepository<UserCertEntity,Integer> {
    Optional<UserCertEntity> findByUserId(int userId);
}
