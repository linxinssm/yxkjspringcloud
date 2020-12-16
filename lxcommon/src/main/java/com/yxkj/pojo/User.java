package com.yxkj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel("用户实体类")
@Entity(name = "user")
@Data
public class User {

    @ApiModelProperty(value = "用户ID")
    @Id//必须加
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid ;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "用户密码")
    private String password;
}
