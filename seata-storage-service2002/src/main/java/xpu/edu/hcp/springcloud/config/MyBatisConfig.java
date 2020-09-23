package xpu.edu.hcp.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"xpu.edu.hcp.springcloud.dao"})
public class MyBatisConfig {

}

