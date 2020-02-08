package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "stations")
public class Station extends BaseEntity {

    public Station() {
    }

    public Station(int id, String name) {
        super(id);
        this.name = name;
    }

    public Station(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="departureStation", fetch= FetchType.EAGER)
    private Collection<Flight> departureFlights;

    @OneToMany(mappedBy="arrivalStation", fetch= FetchType.EAGER)
    private Collection<Flight> arrivalFlights;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
