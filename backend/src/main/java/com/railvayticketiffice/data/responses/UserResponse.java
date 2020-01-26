package com.railvayticketiffice.data.responses;


import com.railvayticketiffice.entity.User;

public class UserResponse {

    public UserResponse(){}

    public UserResponse(Integer id, String login, String role, String name, String surname, double funds) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.funds = funds;
    }

    private Integer id;
    private String login;
    private String role;
    private String name;
    private String surname;
    private double funds;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.funds = user.getFunds();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
}
