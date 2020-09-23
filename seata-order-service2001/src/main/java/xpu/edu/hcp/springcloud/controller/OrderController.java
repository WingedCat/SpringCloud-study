package xpu.edu.hcp.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xpu.edu.hcp.springcloud.entity.CommonResult;
import xpu.edu.hcp.springcloud.entity.Order;
import xpu.edu.hcp.springcloud.service.OrderService;

@RestController
public class OrderController{
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}