package com.example.appletnewpay.entity;

import lombok.Data;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-02 16:25
 **/
@Data
public class TemplateParam {
    // 参数名称
    private String name;
    // 参数值
    private String value;
    // 颜色
    private String color;

    public TemplateParam(String name,String value,String color){
        this.name=name;
        this.value=value;
        this.color=color;
    }
}
