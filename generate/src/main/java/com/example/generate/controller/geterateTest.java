package com.example.generate.controller;

import org.mybatis.generator.api.ShellRunner;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-05 16:30
 **/
public class geterateTest {
    public static void main(String[] args) {
        args = new String[] { "-configfile", "src\\main\\resources\\mybatis-generator-config.xml", "-overwrite" };
        ShellRunner.main(args);
    }

}
