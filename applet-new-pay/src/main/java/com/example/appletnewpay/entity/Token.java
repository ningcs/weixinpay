package com.example.appletnewpay.entity;

import lombok.Data;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-02 15:47
 **/
@Data
public class Token {
    //接口访问凭证
    private String accessToken;
    //有效期限
    private int expiresIn;
    //获取token的最新时间
    private long addTime;
    //签名
    private String ticket;
}