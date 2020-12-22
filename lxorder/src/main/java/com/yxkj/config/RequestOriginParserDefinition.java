package com.yxkj.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


//sentinel 的 授权规则  让会员访问   非会员不能访问

//黑白名单
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    //区分来源  :  通过resquest域获取来源标识
    //会员  非会员
    //将标识交给流控应用  位置进行匹配
    @Override
    public String parseOrigin(HttpServletRequest request) {

        String serviceName = request.getParameter("serviceName");

        if (StringUtils.isEmpty(serviceName)){
            throw  new RuntimeException("serviceName is not null ");
        }
        return serviceName;
    }
}
