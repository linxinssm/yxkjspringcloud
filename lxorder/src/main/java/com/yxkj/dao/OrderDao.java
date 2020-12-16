package com.yxkj.dao;

import com.yxkj.pojo.Shorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Shorder,Integer> {
}
