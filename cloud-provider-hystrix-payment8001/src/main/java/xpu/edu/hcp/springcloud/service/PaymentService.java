package xpu.edu.hcp.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+ "  paymentInfo_OK,id:"+id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUT_Handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TIMEOUT(Integer id){
        try {
            System.out.println("*********** id = "+id);
            int time = id * 1000;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+ "  paymentInfo_TIMEOUT,id:"+id;
    }

    private String paymentInfo_TIMEOUT_Handler(Integer id){
        return "线程池："+Thread.currentThread().getName()+ "  paymentInfo_TIMEOUT,id:"+id+"，  超时了~~";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }



}
