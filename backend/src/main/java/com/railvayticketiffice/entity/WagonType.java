package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wagon_types")
public class WagonType extends BaseEntity {

    public WagonType() {
    }

    public WagonType(int id, int numberOfSeats, int comfort, String name) {
        super(id);
        this.numberOfSeats = numberOfSeats;
        this.comfort = comfort;
        this.name = name;
    }

    public WagonType(String name, int numberOfSeats, int comfort) {
        this.numberOfSeats = numberOfSeats;
        this.comfort = comfort;
        this.name = name;
    }

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "comfort")
    private int comfort;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="wagonType", fetch= FetchType.EAGER)
    private Collection<Wagon> wagons;

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
