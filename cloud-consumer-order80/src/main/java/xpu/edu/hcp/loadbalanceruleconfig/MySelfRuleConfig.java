package xpu.edu.hcp.loadbalanceruleconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRuleConfig  {

    @Bean
    public IRule myRule(){
        //使用随机
        return new RandomRule();
    }
}
