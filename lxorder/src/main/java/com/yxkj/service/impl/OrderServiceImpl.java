package com.yxkj.service.impl;

import com.yxkj.dao.OrderDao;
import com.yxkj.pojo.Shorder;
import com.yxkj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Shorder getOrder(int uid) {
        Shorder shorder = orderDao.findById(uid).get();
        if (!ObjectUtils.isEmpty(shorder)) {
            return shorder;
        }
        return null;
    }
}
