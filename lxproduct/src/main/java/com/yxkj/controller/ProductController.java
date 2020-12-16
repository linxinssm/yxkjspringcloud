package com.yxkj.controller;


import com.yxkj.pojo.Product;
import com.yxkj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.sound.sampled.Port;

@RestController
@RequestMapping("pro1")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("{pid}")
    public Product getProduct(@PathVariable int pid){

        log.info("查询用户信息");
        System.out.println(port);
        return productService.grtProDuct(pid);
    }

}
