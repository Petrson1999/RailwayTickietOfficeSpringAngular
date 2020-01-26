package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    public Ticket(){}

    public Ticket(int id, Flight flight, User user, double cost, Seat seatId){
        super(id);
        this.flight = flight;
        this.user = user;
        this.cost = cost;
        this.seat = seat;
    }

    public Ticket(Flight flight, User user, double cost, Seat seatId){
        this.flight = flight;
        this.user = user;
        this.cost = cost;
        this.seat = seat;
    }


    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="flight_id")
    private Flight flight;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "cost")
    private double cost;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="seat_id")
    private Seat seat;

    @OneToMany(mappedBy="ticket", fetch=FetchType.EAGER)
    private Collection<Luggage> luggages;

    public Collection<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(Collection<Luggage> luggages) {
        this.luggages = luggages;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
