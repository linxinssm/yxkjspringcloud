package com.yxkj.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "shorder")
@Data
public class Shorder {


    @Id
    private String oid;

    //用户信息
    private Integer uid;
    private String  uname;

    //商品
    private Integer pid;
    private String pname;
    private String loc;

    //数量
    private Integer  number;
}
