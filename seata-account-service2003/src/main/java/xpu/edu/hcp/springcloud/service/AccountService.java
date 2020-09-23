package xpu.edu.hcp.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xpu.edu.hcp.springcloud.entity.CommonResult;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 扣减账户余额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}

