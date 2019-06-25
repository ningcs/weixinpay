package com.example.appletnewpay.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-06-25 15:06
 **/
@Data
public class NewWXOrderRequest {
    @NotNull
    @Min(value = 1)
    private Integer quantity; // 货物数量
    @NotNull
    private String openId; // 小程序的用户openId
//    private String orderRemark; // 订单备注留言
//    // 地址
//    private String name; // 收货人姓名
//    private String phone; // 收货人联系方式
//    private String province; // 省份
//    private String city; // 城市
//    private String district; // 区
//    private String address; // 详细地址
}
