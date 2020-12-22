package com.yxkj.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//自己定义的异常处理类
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        //处理响应回去有中文乱码问题
        response.setContentType("application/json;charset=utf-8");

       /*
       FlowException 限流
       DegradeException 降级
       AuthorityException 授权
        ParamFlowException 热点
        SystemBlockException  系统
        */
        //BlockException异常 的子类 包含了五种sentinel的 五种规则异常 方法级

        ResponseDate responseDate = null;
        if (e instanceof FlowException) {
            responseDate = new ResponseDate(1, "接口被限流了");
        } else if (e instanceof DegradeException) {
            responseDate = new ResponseDate(2, "接口被降级了");
        }else if (e instanceof ParamFlowException) {
            responseDate = new ResponseDate(3, "热点规则");
        } else if (e instanceof AuthorityException) {
            responseDate = new ResponseDate(5, "接口被授权了");
        }
        //将对象以JSON的格式响应回去
        response.getWriter().write(JSON.toJSONString(responseDate));
    }
}

@Data
@AllArgsConstructor //全参构造
@NoArgsConstructor
        //无参构造
class ResponseDate {

    private int code;
    private String message;

}