package com.yxkj.service.fallback;

import com.yxkj.pojo.Product;
import com.yxkj.service.ProductService;
import org.springframework.stereotype.Component;

//这是一个容错类 需要实现Fegin所在的接口 并去实现接口中的所有方法
//一旦fegin远程调用出现问题就会进入容错类
@Component
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product getProduct(int pid) {
        //容错容器
        Product product = new Product();
        product.setPid(-100);
        product.setPname("远程服务调用失败");
        return product;
    }
}
