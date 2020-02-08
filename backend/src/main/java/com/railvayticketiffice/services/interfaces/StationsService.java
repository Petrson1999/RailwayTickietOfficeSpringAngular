package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.AddStationRequest;
import com.railvayticketiffice.entity.Station;

import java.util.List;

public interface StationsService {

    List<Station> getAllStations();

    boolean addStation(AddStationRequest addStationRequest);

}
