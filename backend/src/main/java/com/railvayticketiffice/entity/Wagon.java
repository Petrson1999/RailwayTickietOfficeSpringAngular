package com.railvayticketiffice.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wagons")
public class Wagon extends BaseEntity {

    public Wagon() {
    }

    public Wagon(int id, Train train, WagonType wagonType, String name) {
        super(id);
        this.setTrain(train);
        this.wagonType = wagonType;
        this.name = name;
    }

    public Wagon(WagonType wagonType, String name) {
        this.wagonType = wagonType;
        this.name = name;
    }

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="type_id")
    private WagonType wagonType;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="wagon", fetch=FetchType.EAGER)
    private Collection<Seat> seats;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public WagonType getWagonType() {
        return wagonType;
    }

    public void setWagonType(WagonType wagonType) {
        this.wagonType = wagonType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<Seat> seats) {
        this.seats = seats;
    }
}
