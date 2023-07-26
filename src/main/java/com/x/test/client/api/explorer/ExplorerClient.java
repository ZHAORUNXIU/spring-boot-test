package com.x.test.client.api.explorer;

import com.x.test.client.model.explorer.vo.TransactionHistoryVO;
import com.x.test.common.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "explorerClient", url = "${feign.explorerClient.url}")
public interface ExplorerClient {

    @GetMapping("/v1/transaction/history")
    Result<List<TransactionHistoryVO>> getTransactionHistory();
}
