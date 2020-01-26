package com.railvayticketiffice.data.requests;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {

    public SignUpRequest(){}

    public SignUpRequest(@NotNull @NotBlank @Size(min = 3, max = 24) String login, @NotNull @NotBlank @Size(min = 6, max = 64) String password, @NotNull @NotBlank String role, @NotNull @NotBlank String name, @NotNull @NotBlank String surname) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    @NotNull
    @NotBlank
    @Size(min = 3, max = 24)
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 64)
    private String password;

    @NotNull
    @NotBlank
    private String role;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
