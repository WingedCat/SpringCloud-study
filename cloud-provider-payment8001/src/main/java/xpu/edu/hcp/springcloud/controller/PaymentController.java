package xpu.edu.hcp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import xpu.edu.hcp.springcloud.entities.CommonResult;
import xpu.edu.hcp.springcloud.entities.Payment;
import xpu.edu.hcp.springcloud.service.IPaymentService;

import java.util.List;

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

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element: {}",service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("serviceId: {},host：{}，port：{},uri：{}",instance.getInstanceId(),instance.getHost(),instance.getPort(),instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("lb")
    public String getPort(){
        return port;
    }

    @GetMapping("zipkin")
    public String zipkin(){
        return "hi,i'am payment zipkin server fall back,welcome ~";
    }
}
