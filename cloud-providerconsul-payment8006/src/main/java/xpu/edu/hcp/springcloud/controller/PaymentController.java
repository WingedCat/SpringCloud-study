package xpu.edu.hcp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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


    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("consul")
    public String zktest(){
        return "SpringCloud with consul: "+ UUID.randomUUID().toString();
    }
}
