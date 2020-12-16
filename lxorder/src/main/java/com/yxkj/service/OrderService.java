package com.yxkj.service;

import com.yxkj.pojo.Shorder;

public interface OrderService {
    Shorder getOrder(int uid);

    boolean saveOrder(Shorder shorder);
}
