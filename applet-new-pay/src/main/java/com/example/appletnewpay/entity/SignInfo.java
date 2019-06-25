package com.example.appletnewpay.entity;

import lombok.Data;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-06-25 15:02
 **/
@Data
public class SignInfo {
    private String appId;//小程序ID
    private String timeStamp;//时间戳
    private String nonceStr;//随机串
    //@XStreamAlias("package")
    private String repay_id;
    private String signType;//签名方式

}
