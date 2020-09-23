package xpu.edu.hcp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpu.edu.hcp.springcloud.entities.CommonResult;
import xpu.edu.hcp.springcloud.entities.Payment;
import xpu.edu.hcp.springcloud.service.PaymentFeignService;

@RestController
@Slf4j
@RequestMapping("/order/")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult payment = paymentFeignService.getPaymentById(id);
        return payment;
    }
}
