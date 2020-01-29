package com.railvayticketiffice.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "flights")
public class Flight extends BaseEntity {

    public Flight() {
    }

    public Flight(int id, Station departureStation, Station arrivalStation, LocalDateTime departureTime, LocalDateTime arrivalTime,
                  double cost, String name, Train train) {
        super(id);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
        this.name = name;
        setTrain(train);
    }

    public Flight(Station departureStation, Station arrivalStation, LocalDateTime departureTime, LocalDateTime arrivalTime,
                  double cost, String name, Train train) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
        this.name = name;
        setTrain(train);
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_station_id")
    private Station arrivalStation;

    @Column(name = "departure_time", columnDefinition = "DATE")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", columnDefinition = "DATE")
    private LocalDateTime arrivalTime;

    @Column(name = "cost")
    private double cost;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    private Train train;

    @OneToMany(mappedBy="flight", fetch=FetchType.EAGER)
    private Collection<Ticket> tickets;

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }


    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Train getTrain() {
        return train;
    }



}
