package com.yxkj.controller;


import com.alibaba.fastjson.JSON;
import com.yxkj.pojo.Product;
import com.yxkj.pojo.Shorder;
import com.yxkj.pojo.User;
import com.yxkj.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.UUID;


/*http://localhost:8071/swagger-ui.html#/  swagger2访问地址*/
@RestController
@RequestMapping("order1")
@Slf4j
@Api(tags ="订单测试")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    //注入客户端
    @Autowired
    private DiscoveryClient discoveryClient;


    @ApiOperation("生成订单")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name="pid",value="商品ID",required = true),
            @ApiImplicitParam(name="id",value="用户ID")
    })
    @RequestMapping("{pid}/{id}")
    public Shorder getOrder(@PathVariable  int pid ,@PathVariable int id){

        log.info("生成订单");

        Shorder shorder = new Shorder();
        //基于RestTemplate的 服务调用
        //查询产品信息

        //1. 不灵活 无法负载均衡
        //product1 product1 = restTemplate.getForObject("http://localhost:8081/pro1/"+pid, product1.class);

        //2. Nacos服务治理 = Eureka + Config  不能做到负载均衡
     /*   从Nacos中获取微服务的实例,如果是集群就是得到集合
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost(); //IP地址
        System.out.println("ip地址" + host);
        int port = serviceInstance.getPort(); //
        System.out.println("端口" + port);

        Product product1 = restTemplate.getForObject("http://" + host +":"+port+"/pro1/"+pid, Product.class);
     */
        //3.Nacos服务治理 + 负载均衡(自定义随机算法)
      /*  List<ServiceInstance> instances = discoveryClient.getInstances("service-product"); //获取服务实例
        int i = new Random().nextInt(instances.size()); // 随机数再集合的长度内
        ServiceInstance serviceInstance = instances.get(i);
        System.out.println("IP地址::"+serviceInstance.getHost() + "端口::" + serviceInstance.getPort());
        Product product1 = restTemplate.getForObject("http://" + serviceInstance.getHost() +":"+serviceInstance.getPort()+"/pro1/"+pid, Product.class);
      */

        //4.Nacos服务治理 + Ribbon负载均衡(包含很多均衡策略 :默认轮询机制)
        //在yml中可以进行负载均衡的策略设置
        Product product1 = restTemplate.getForObject("http://service-product/pro1/"+pid, Product.class);

        User user = restTemplate.getForObject("http://service-user/use/mes1/" + id, User.class);

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
        return  shorder;
    }
}
