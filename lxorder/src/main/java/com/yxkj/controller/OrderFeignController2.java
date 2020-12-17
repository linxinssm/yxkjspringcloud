package com.yxkj.controller;

import com.alibaba.fastjson.JSON;
import com.yxkj.pojo.Product;
import com.yxkj.pojo.Shorder;
import com.yxkj.pojo.User;
import com.yxkj.service.OrderService;
import com.yxkj.service.ProductService;
import com.yxkj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

//基于Feign的服务调用   http://localhost:8091/swagger-ui.html#/  swagger2访问地址
@RestController
@RequestMapping("order2")
@Slf4j
@Api(tags = "订单测试")
public class OrderFeignController2 {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @ApiOperation("生成订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pid", value = "商品ID", required = true),
            @ApiImplicitParam(name = "id", value = "用户ID")
    })
    @RequestMapping("{pid}/{id}")
    public Shorder getOrder(@PathVariable int pid, @PathVariable int id) {

        log.info("生成订单");
        //基于Feign的服务调用
        Shorder shorder = new Shorder();

        //1.Nacos服务治理 + Ribbon负载均衡(包含很多均衡策略 :默认轮询机制) + feign服务调用
        //在yml中可以进行负载均衡的策略设置

        Product product1 = productService.getProduct(pid);
        User user = userService.getUserBy(id);

        log.info("查询到的商品信息{}...", JSON.toJSONString(product1));
        String oid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);

        shorder.setOid(oid);
        shorder.setLoc(product1.getLoc());
        shorder.setNumber(3);
        shorder.setPid(product1.getPid());
        shorder.setPname(product1.getPname());
        shorder.setUname(user.getName());
        shorder.setUid(user.getUid());

        // Boolean boo = orderService.saveOrder(shorder);
        /*if (boo){
            log.info("订单保存成功");
            return  shorder;
        }*/
        log.info("订单生成成功");
        return shorder;
    }


    @RequestMapping("or1")
    public String getOrderBy(int pid) {

        //让线程睡2秒
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("商品查询成功{}", pid);
        productService.getProduct(pid);
        return "世界如此美丽,我却如此多焦";
    }



}
