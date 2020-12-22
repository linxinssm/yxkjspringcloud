package com.yxkj.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import com.yxkj.pojo.Product;
import com.yxkj.pojo.Shorder;
import com.yxkj.service.OrderService;
import com.yxkj.service.ProductService;
import com.yxkj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("sentinel")  // 服务容错sentinel Alibaba : 熔断降级 流量控制 系统负载保护
@Slf4j                       // 在sentinel的控制台上设置控制规则
public class OrderSentinelController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    /*Feign + Sentinel + Nacos +Ribbon*/

    int i =0;

    @RequestMapping("sent1")
    public Product mse(int pid) {

        Product product = productService.getProduct(pid);
        log.info("商品查询成功{}", JSON.toJSONString(product));
        return product;
    }

    @RequestMapping("mes")
    public String message() {
        String str = userService.getUser();
        log.info("用户查询成功{}",JSON.toJSONString(str));
        //模拟一个异常比率
        i++;
        if (i % 3 == 0){
            throw new RuntimeException();
        }
        return str;
    }

    @RequestMapping("mes1")
    public Shorder getMes(int uid) {
        Shorder order = orderService.getOrder(uid);
        log.info("用户查询成功{}",JSON.toJSONString(order));
        return order;
    }



    @RequestMapping("mes2")
    @SentinelResource("mes2")
    public String getMesss(String name ,Integer age) {
        System.out.println("我的年龄和姓名" + name + " :: " + age);
        return "我的年龄和姓名" + name + " :: " + age;
    }
}
