package com.x.test.gateway.action;

import com.x.test.api.model.domain.user.UserMongoDO;
import com.x.test.api.service.user.UserService;
import com.x.test.client.api.explorer.ExplorerClient;
import com.x.test.client.model.explorer.vo.TransactionHistoryVO;
import com.x.test.common.model.Result;
import com.x.test.common.util.Log;
import com.x.test.gateway.model.req.AddUserReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/transaction")
@Validated
@Component
@Api(tags = "Transaction API", description = "API endpoints for managing transaction")
public class TransactionAction {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionAction.class);

    @Resource
    private ExplorerClient explorerClient;

    /**
     * mongo test: Add to MongoDB
     */
    @PostMapping("/history")
    @ApiOperation(value = " get transaction history from explorer Client", response = Result.class)
    public Result<List<TransactionHistoryVO>> getHistory() {

        Result<List<TransactionHistoryVO>> result = explorerClient.getTransactionHistory();

        LOG.info(Log.format("Done", Log.kv("success:", result.isSuccess())));

        if (!result.isSuccess()) {
            return Result.failure(result);
        }

        LOG.info(Log.format("success", Log.kv("size:", result.getData().size())));

        return Result.success(result.getData());
    }
}
