package xpu.edu.hcp.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import xpu.edu.hcp.springcloud.entities.CommonResult;

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
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"客户自定义全局限流异常",null);
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4555,"客户自定义全局限流异常2",null);
    }
}
