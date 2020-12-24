package com.yxkj.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yxkj.pojo.Product;
import com.yxkj.service.OrderService;
import com.yxkj.service.ProductService;
import com.yxkj.service.UserService;
import com.yxkj.service.exceptionhandler.BlockHandlerException;
import com.yxkj.service.exceptionhandler.FallBackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order3")
@Slf4j
public class OrderSentinelResourceController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping("mes/{pid}")
    @SentinelResource(value = "mes",
            blockHandler = "blockHandler",
            fallback = "fallback")
    public  String  getMes(String name , int age , @PathVariable int pid){

        Product product = productService.getProduct(pid);
        return name + ":::" + age;
    }


    //把异常的方法抽取出去
    @RequestMapping("mes1")
    @SentinelResource(value = "mes1",
            blockHandlerClass = BlockHandlerException.class,
            blockHandler = "blockHandler",
            fallbackClass = FallBackException.class,
            fallback = "fallback")
    public  String  getMes1(String name , int age){

        int i = 1/0;

        return name + ":::" + age;
    }




    //监听sentinel的五种规则抛出的异常 , 要求
    // 当前方法的返回值和参数必须跟原方法一致
    //但是允许在参数列表的最后一个参数,加上BlockException
   /* public String blockHandler(String name , int age, BlockException e){
        //自定义异常处理逻辑

        log.error("出发了BlockException...");

        return "BlockException";
    }
*/
    //监听所偶遇异常
    // 当前方法的返回值和参数必须跟原方法一致
    //但是允许在参数列表的最后一个参数,加上Throwable
    /*public String fallback(String name , int age, Throwable e){
        //自定义异常处理逻辑

        log.error("触发全局异常Throwable...");

        return "Throwable";
    }*/





}
