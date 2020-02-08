package com.railvayticketiffice.entity;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public User() {
    }

    public User(int id, String login, String password, String role, String name, String surname, double funds) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.funds = funds;
    }

    public User(String login, String password, String role, String name, String surname) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.funds = 100;
    }

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "funds")
    private double funds;

    @OneToMany(mappedBy="user", fetch= FetchType.EAGER)
    private Collection<Ticket> tickets;

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double founds) {
        this.funds = founds;
    }

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
