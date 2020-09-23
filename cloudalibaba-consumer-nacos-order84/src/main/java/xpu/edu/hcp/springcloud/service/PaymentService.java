package xpu.edu.hcp.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xpu.edu.hcp.springcloud.entities.CommonResult;
import xpu.edu.hcp.springcloud.entities.Payment;
import xpu.edu.hcp.springcloud.service.impl.PaymentFallbackService;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService
{
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}

