package xpu.edu.hcp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {

        return "------testB";
    }

    @GetMapping("/testC")
    public String testD()
    {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testC 测试RT");

            return "------testC";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealTestHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,@RequestParam(value = "p2",required = false)String p2){
        return "----------testHotKey";
    }

    public String dealTestHotKey(String p1, String p2, BlockException exception){
        return "热点规则拒绝";
    }



}
