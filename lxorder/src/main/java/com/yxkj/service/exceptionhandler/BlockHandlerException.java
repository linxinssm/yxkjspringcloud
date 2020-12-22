package com.yxkj.service.exceptionhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

//BlockHandler异常处理类
@Slf4j
public class BlockHandlerException {

    //这里有个小坑 , 该发法必须是static
    public static String blockHandler(String name , int age, BlockException e){
        //自定义异常处理逻辑

        log.error("出发了BlockException...");

        return "BlockException触发了sentinel的五种异常";
    }

}
