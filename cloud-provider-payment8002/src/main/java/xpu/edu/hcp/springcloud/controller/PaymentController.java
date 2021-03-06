package xpu.edu.hcp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import xpu.edu.hcp.springcloud.entities.CommonResult;
import xpu.edu.hcp.springcloud.entities.Payment;
import xpu.edu.hcp.springcloud.service.IPaymentService;

/**                                                                                ____________________
 _                _                                                           < 神兽护体，永无bug! >
 | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
 | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
 | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
 |___/|_|                |___/                                ||----w |
 ||     ||
 * @author huchengpeng
 */
@Slf4j
@RestController
@RequestMapping("/payment/")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入订单，结果为：{}",result);
        if(result > 0){
            return new CommonResult(200,"插入数据库成功,来自："+port,result);
        }
        return new CommonResult(444,"插入数据库失败,来自："+port);
    }

    @GetMapping(value = "get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询id为 {} 的订单，结果为：{}",id,payment);
        if (payment != null) {
            return new CommonResult(200,"查询数据库成功,来自："+port,payment);
        }
        return new CommonResult(444,"没有对应记录,来自："+port);
    }

    @GetMapping("lb")
    public String getPort(){
        return port;
    }
}
