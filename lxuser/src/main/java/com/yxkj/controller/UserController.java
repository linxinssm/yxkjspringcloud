package com.yxkj.controller;

import com.yxkj.pojo.User;
import com.yxkj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户测试")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("mes")
    public  String getUser(){
        return "Holler , world!!";
    }

    @ApiOperation("测试方法hello")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name="id",value="用户ID",required = true)
    })
    @RequestMapping("mes1/{id}")
    public User getUserBy(@PathVariable("id") int uid){
        return userService.getUserBy(uid);
    }
}