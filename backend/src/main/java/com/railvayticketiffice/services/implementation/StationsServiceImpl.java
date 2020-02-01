package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.requests.AddStationRequest;
import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.dao.repositories.StationRepository;
import com.railvayticketiffice.services.interfaces.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationsServiceImpl implements StationsService {

    @Autowired
    public StationsServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    private StationRepository stationRepository;

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public boolean addStation(AddStationRequest addStationRequest) {
        Station station = new Station();
        station.setName(addStationRequest.getStationName());
        return stationRepository.saveAndFlush(station) != null;
    }
}
