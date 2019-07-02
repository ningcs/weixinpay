package com.example.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-01 13:40
 **/
@Data
@ApiModel("苹果")
public class Apple {
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("颜色")
    private String color;
    @ApiModelProperty("名字")
    private String name;
}
