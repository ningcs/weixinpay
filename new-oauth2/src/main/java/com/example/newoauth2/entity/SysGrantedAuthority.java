package com.example.newoauth2.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @description:授权权限模型
 * @author: ningcs
 * @create: 2019-07-03 16:45
 **/
public class SysGrantedAuthority /*extends BaseModel*/ implements GrantedAuthority {

    private static final long serialVersionUID = 5698641074914331015L;

    /**
     * 权限
     */
    private String authority;

    /**
     * 权限
     * @return authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 权限
     * @param authority 权限
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}