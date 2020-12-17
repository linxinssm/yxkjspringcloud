package com.yxkj.service;

import com.yxkj.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-user")
public interface UserService {

   // User user = restTemplate.getForObject("http://service-user/use/mes1/" + id, User.class);

   /*@FeignClient的value值 +  @RequestMapping("{pid}")的value值 就是一个完整的url路径*/

    @RequestMapping("use/mes1/{id}")
    public User getUserBy(@PathVariable("id") int uid);

    @RequestMapping("use/mes")
    public  String getUser();
}
