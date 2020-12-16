package com.yxkj.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@ApiModel("订单实体类")
@Entity(name = "shorder")
@Data
public class Shorder {

    @ApiModelProperty(value = "订单ID")
    @Id
    private String oid;

    //用户信息
    @ApiModelProperty(value = "用户ID")
    private Integer uid;
    @ApiModelProperty(value = "用户姓名")
    private String  uname;

    //商品
    @ApiModelProperty(value = "商品ID")
    private Integer pid;
    @ApiModelProperty(value = "商品名称")
    private String pname;
    @ApiModelProperty(value = "商品地址")
    private String loc;

    //数量
    @ApiModelProperty(value = "购买商品的数量")
    private Integer  number;
}
