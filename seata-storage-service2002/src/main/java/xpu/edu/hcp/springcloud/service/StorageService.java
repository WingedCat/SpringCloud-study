package xpu.edu.hcp.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xpu.edu.hcp.springcloud.entity.CommonResult;

public interface StorageService {

    // 扣减库存
    void decrease(Long productId, Integer count);
}
