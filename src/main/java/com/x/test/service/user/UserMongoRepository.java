package com.x.test.service.user;

import com.x.test.api.model.domain.user.UserMongoDO;
import com.x.test.common.repository.BaseMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends BaseMongoRepository<UserMongoDO, String> {

    Optional<UserMongoDO> findByUserId(Long userId);

    Optional<UserMongoDO> findByPhone(String phone);
}
