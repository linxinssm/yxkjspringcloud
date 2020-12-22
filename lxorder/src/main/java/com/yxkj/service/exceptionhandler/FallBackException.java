package com.yxkj.service.exceptionhandler;

import lombok.extern.slf4j.Slf4j;

//fallback异常处理类 全局异常throwable

@Slf4j
public class FallBackException {

    //这里有个小坑 , 该发法必须是static
    public static String fallback(String name , int age, Throwable e){
        //自定义异常处理逻辑

        log.error("触发全局异常Throwable...");

        return "Throwable触发全局异常";
    }

}
