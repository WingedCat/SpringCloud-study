package xpu.edu.hcp.springcloud.service.impl;

import org.springframework.stereotype.Component;
import xpu.edu.hcp.springcloud.entities.CommonResult;
import xpu.edu.hcp.springcloud.entities.Payment;
import xpu.edu.hcp.springcloud.service.PaymentService;

@Component
public class PaymentFallbackService implements PaymentService
{
    @Override
    public CommonResult<Payment> paymentSQL(Long id)
    {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
