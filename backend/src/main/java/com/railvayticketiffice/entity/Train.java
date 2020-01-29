package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "trains")
public class Train extends BaseEntity {

    public Train(){}

    public Train(int id, String name, Locomotive locomotive){
        super(id);
        this.name = name;
        this.locomotive = locomotive;
    }

    public Train(String name){
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="locomotive_id")
    private Locomotive locomotive;

    @OneToMany(mappedBy="train", fetch=FetchType.EAGER)
    private Collection<Wagon> wagons;

    @OneToMany(mappedBy="train", fetch=FetchType.EAGER)
    private Collection<Flight> flights;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public Collection<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(Collection<Wagon> wagons) {
        this.wagons = wagons;
    }

    public Collection<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Collection<Flight> flights) {
        this.flights = flights;
    }
}
