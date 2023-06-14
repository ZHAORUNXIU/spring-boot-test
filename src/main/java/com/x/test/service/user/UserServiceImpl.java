package com.x.test.service.user;

import com.x.test.api.model.domain.user.UserMongoDO;
import com.x.test.api.service.user.UserService;
import com.x.test.common.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMongoRepository userRepository;

    @Override
    public Result<String> add(UserMongoDO userDO) {
        userDO.setUserId(generateUserId());
        userDO = userRepository.insert(userDO);
//        Optional<UserDO> optional = userRepository.findByPhone(userMongoDO.getPhone());
        return Result.success(userDO.getId());
    }

    private Long generateUserId() {
        Random random = new Random();
        int min = 100000000;
        int max = 999999999;
        int userId = random.nextInt(max - min + 1) + min;
        return Long.valueOf(userId);
    }
}
