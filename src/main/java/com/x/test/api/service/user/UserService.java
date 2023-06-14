package com.x.test.api.service.user;

import com.x.test.api.model.domain.user.UserMongoDO;
import com.x.test.common.model.Result;

public interface UserService {

    Result<String> add(UserMongoDO userDO);
}
