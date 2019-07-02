package com.example.oauth.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: ningcs
 * @create: 2019-07-01 10:43
 **/
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
