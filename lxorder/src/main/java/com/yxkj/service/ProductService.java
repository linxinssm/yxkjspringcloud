package com.yxkj.service;

import com.yxkj.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product") //value 用于指定调用 Nacos下的那个微服务
public interface ProductService {

    //Product product1 = restTemplate.getForObject("http://service-product/pro1/"+pid, Product.class);

    //@FeignClient的value值 +  @RequestMapping("{pid}")的value值 就是一个完整的url路径

    @RequestMapping("pro1/{pid}")
    public Product getProduct(@PathVariable int pid); //复制ProductController里的方法小括号以前一致

}
