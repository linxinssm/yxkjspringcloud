package com.yxkj.service.impl;

import com.yxkj.dao.ProductDao;
import com.yxkj.pojo.Product;
import com.yxkj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product grtProDuct(int pid) {
        Product product = productDao.findById(pid).get();
        return ObjectUtils.isEmpty(product) ? null : product;
    }
}
