package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "flights")
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

}
