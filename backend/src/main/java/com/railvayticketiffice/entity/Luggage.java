package com.railvayticketiffice.entity;

import javax.persistence.*;

@Entity
@Table(name = "luggage")
public class Luggage extends BaseEntity {

    public Luggage() {
    }

    public Luggage(int id, Ticket ticket) {
        super(id);
        this.ticket = ticket;
    }

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="seat_id")
    private Ticket ticket;

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
