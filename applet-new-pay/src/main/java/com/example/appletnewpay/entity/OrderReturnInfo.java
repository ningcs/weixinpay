package com.example.appletnewpay.entity;

import lombok.Data;

/**
 * @description: 订单返回消息实体类
 * @author: ningcs
 * @create: 2019-06-25 15:01
 **/
@Data
public class OrderReturnInfo {
    private String return_code;
    private String return_msg;
    private String result_code;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String prepay_id;
    private String trade_type;

}
