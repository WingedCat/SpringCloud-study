package xpu.edu.hcp.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpu.edu.hcp.springcloud.service.OrderHystrixService;

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
@RestController
@Slf4j
@RequestMapping("/order/")
@DefaultProperties(defaultFallback = "globalFallBack")
public class OrderHystrixController {

    @Autowired
    private OrderHystrixService orderHystrixService;

    @GetMapping("payment/ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        return orderHystrixService.ok(id);
    }

    @GetMapping("payment/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUT_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout(@PathVariable("id") Integer id) {
        return orderHystrixService.timeout(id);
    }

    private String paymentInfo_TIMEOUT_Handler(Integer id) {
        return "客户端，超时了~~,id:" + id;
    }

    //全局fallback
    public String globalFallBack() {
        return "globalFallBack...";
    }
}