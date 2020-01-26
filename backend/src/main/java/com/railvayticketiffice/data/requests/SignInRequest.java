package com.railvayticketiffice.data.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInRequest {

    public SignInRequest(){}

    public SignInRequest(String login, String password){
        this.login = login;
        this.password = password;
    }

    @NotBlank
    @Size(min = 3, max = 24)
    private String login;

    @NotBlank
    @Size(min = 6, max = 64)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
