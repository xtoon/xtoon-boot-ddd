package com.xtoon.boot.util.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author haoxin
 * @date 2021-01-25
 **/
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
