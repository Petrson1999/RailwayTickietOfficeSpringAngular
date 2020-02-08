package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "locomotives")
public class Locomotive  extends BaseEntity {

    public Locomotive() {
    }

    public Locomotive(int id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="locomotive", fetch=FetchType.EAGER)
    private Collection<Train> trains;

    public void setTrains(Collection<Train> trains) {
        this.trains = trains;
    }

    public Collection<Train> getTrains() {
        return trains;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
