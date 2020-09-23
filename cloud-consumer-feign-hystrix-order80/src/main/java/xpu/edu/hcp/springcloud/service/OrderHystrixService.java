package xpu.edu.hcp.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = OrderFallbackService.class)
public interface OrderHystrixService {
    @GetMapping(value = "payment/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id);

    @GetMapping(value = "payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id);
}
