package com.yxkj.service.fallback;

import com.yxkj.pojo.Product;
import com.yxkj.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//此处的泛型 是指 :  你要调用的接口
@Slf4j
@Service
public class ProductFallBackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product getProduct(int pid) {

                log.error("发生的异常是{}",throwable);

                Product product = new Product();
                product.setPid(-100);
                product.setPname("服务调用失败是我的问题...");
                return product;
            }
        };
    }
}
