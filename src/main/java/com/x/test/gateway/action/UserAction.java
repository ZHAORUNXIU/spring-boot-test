package com.x.test.gateway.action;

import com.x.test.api.model.domain.user.UserMongoDO;
import com.x.test.common.model.Result;
import com.x.test.api.service.user.UserService;
import com.x.test.gateway.model.req.AddUserReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/user")
@Validated
@Component
@Api(tags = "User API", description = "API endpoints for managing users")
public class UserAction {

    @Resource
    private UserService userService;

    /**
     * mongo test: Add to MongoDB
     */
    @PostMapping("/add")
    @ApiOperation(value = " insert a new user", response = Result.class)
    public Result<String> add(@Validated @RequestBody AddUserReq req) {
        UserMongoDO userDO = new UserMongoDO();
        BeanUtils.copyProperties(req, userDO);
        Result<String> result = userService.add(userDO);
        if (!result.isSuccess()) {
            return Result.failure(result);
        }
        return Result.success(result.getData());
    }
}
