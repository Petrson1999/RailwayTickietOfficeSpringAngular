package com.railvayticketiffice.data.responses;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    public JwtResponse(){}

    public JwtResponse(String accessToken, String type, Integer userId, Collection<GrantedAuthority> userRoles) {
        this.accessToken = accessToken;
        this.type = type;
        this.userId = userId;
        this.userRoles = userRoles;
    }

    private String accessToken;

    private String type;

    private Integer userId;

    private Collection<GrantedAuthority> userRoles;

    public String getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }

    public Integer getUserId() {
        return userId;
    }

    public Collection<GrantedAuthority> getUserRoles() {
        return userRoles;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserRoles(Collection<GrantedAuthority> userRoles) {
        this.userRoles = userRoles;
    }


}
