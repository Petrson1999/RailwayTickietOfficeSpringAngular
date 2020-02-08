package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "seats")
public class Seat extends BaseEntity {


    public Seat() {
    }

    public Seat(int id, Wagon wagon, int placeNumber) {
        super(id);
        this.wagon = wagon;
        this.placeNumber = placeNumber;
    }

    public Seat(Wagon wagon, int placeNumber) {
        this.wagon = wagon;
        this.placeNumber = placeNumber;
    }

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="wagon_id")
    private Wagon wagon;

    @Column(name = "place_number")
    private int placeNumber;

    @OneToMany(mappedBy="seat", fetch=FetchType.EAGER)
    private Collection<Ticket> tickets;

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}
