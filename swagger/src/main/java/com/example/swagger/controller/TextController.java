package com.example.swagger.controller;

import com.example.swagger.entity.Apple;
import com.example.swagger.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-01 13:39
 **/
@RestController
@Api(tags = "swagger测试")
@RequestMapping("/apple")
public class TextController {


    @ApiOperation("获取苹果列表")
    @PostMapping("/getAppleList")
    public JsonResult getAppleList(String name){
        Apple apple=new Apple();
        apple.setColor("红");
        apple.setId(1L);
        apple.setName("红富士");
        return JsonResult.ok(Arrays.asList(apple));
    }
}
