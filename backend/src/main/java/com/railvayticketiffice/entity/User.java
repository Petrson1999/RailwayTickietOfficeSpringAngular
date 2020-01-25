package com.railvayticketiffice.entity;

import com.railvayticketiffice.enums.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public User() {
    }

    public User(int id, String login, String password, Role role, String name, String surname, double funds) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.funds = funds;
    }

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
