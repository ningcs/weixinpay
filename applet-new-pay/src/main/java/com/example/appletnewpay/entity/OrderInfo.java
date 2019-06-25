package com.example.appletnewpay.entity;

import lombok.Data;

/**
 * @description: 预订单
 * @author: ningcs
 * @create: 2019-06-25 14:59
 **/
@Data
public class OrderInfo {
    private String appid;// 小程序ID
    private String mch_id;// 商户号
    private String nonce_str;// 随机字符串
    private String sign_type;//签名类型
    private String sign;// 签名
    private String body;// 商品描述
    private String out_trade_no;// 商户订单号
    private int total_fee;// 标价金额 ,单位为分
    private String spbill_create_ip;// 终端IP
    private String notify_url;// 通知地址
    private String trade_type;// 交易类型
    private String openid;//用户标识
}