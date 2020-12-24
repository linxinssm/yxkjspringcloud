package com.yxkj.controller;


import com.yxkj.pojo.Product;
import com.yxkj.pojo.Shorder;
import com.yxkj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feign")
@Slf4j
public class FeignSentinelController {


    @Autowired
    private ProductService productService;


    @RequestMapping("mes")
    public Object getFeign(int pid){
        Product product = productService.getProduct(pid);

        if (product.getPid() == -100){
            return product+ "服务调用失败了,下单失败";
        }

        return new Shorder();
    }

}
