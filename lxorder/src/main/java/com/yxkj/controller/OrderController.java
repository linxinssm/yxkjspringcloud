package com.yxkj.controller;


import com.yxkj.pojo.Shorder;
import com.yxkj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("order1")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("{uid}")
    public Shorder getOrder(@PathVariable  int uid){

        return  orderService.getOrder(uid);
    }

}
