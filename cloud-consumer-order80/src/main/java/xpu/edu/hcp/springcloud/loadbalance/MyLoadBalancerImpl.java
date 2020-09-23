package xpu.edu.hcp.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private AtomicInteger nextInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> instances) {
        int nextIndex = getAndIncrement() % instances.size();
        return instances.get(nextIndex);
    }

    public final int getAndIncrement(){
        int current,next;
        for(;;){
            current = nextInteger.get();
            next = current >= Integer.MAX_VALUE  ? 0 : current+1;
            System.out.println("current = "+current+",next = "+next);
            if(nextInteger.compareAndSet(current,next)){
                return next;
            }
        }
    }
}
