package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.dao.repositories.SeatRepository;
import com.railvayticketiffice.dao.repositories.TrainRepository;
import com.railvayticketiffice.dao.repositories.WagonRepository;
import com.railvayticketiffice.dao.repositories.WagonTypeRepository;
import com.railvayticketiffice.data.requests.AddWagonRequest;
import com.railvayticketiffice.data.requests.AddWagonTypeRequest;
import com.railvayticketiffice.dto.SeatDTO;
import com.railvayticketiffice.dto.WagonDTO;
import com.railvayticketiffice.dto.WagonTypeDTO;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.entity.WagonType;
import com.railvayticketiffice.services.interfaces.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WagonsServiceImpl implements WagonService {

    @Autowired
    public WagonsServiceImpl(WagonRepository wagonRepository, WagonTypeRepository wagonTypeRepository, TrainRepository trainRepository, SeatRepository seatRepository) {
        this.wagonRepository = wagonRepository;
        this.wagonTypeRepository = wagonTypeRepository;
        this.trainRepository = trainRepository;
        this.seatRepository = seatRepository;
    }

    private WagonRepository wagonRepository;
    private WagonTypeRepository wagonTypeRepository;
    private TrainRepository trainRepository;
    private SeatRepository seatRepository;

    @Override
    public List<WagonTypeDTO> getAllWagonTypesDTO() {
        List<WagonTypeDTO> wagonTypeDTOS = new ArrayList<>();
        List<WagonType> wagonTypes = wagonTypeRepository.findAll();
        for (WagonType wagonType : wagonTypes) {
            wagonTypeDTOS.add(new WagonTypeDTO(wagonType));
        }
        return wagonTypeDTOS;
    }

    @Override
    public boolean addWagonType(AddWagonTypeRequest addWagonTypeRequest) {
        WagonType wagonType = new WagonType(addWagonTypeRequest);
        return wagonTypeRepository.saveAndFlush(wagonType) != null;
    }

    @Override
    public boolean addWagon(AddWagonRequest addWagonRequest) {
        Train train = trainRepository.findById(addWagonRequest.getTrainId());
        WagonType wagonType = wagonTypeRepository.findById(addWagonRequest.getTypeId());
        Wagon wagon = new Wagon(addWagonRequest, train, wagonType);
        wagon = wagonRepository.saveAndFlush(wagon);
        addSeatOnNewWagon(wagon);
        return wagon != null;
    }

    @Override
    public List<WagonDTO> getAllWagonsDTO() {
        List<WagonDTO> wagonDtos = new ArrayList<>();
        List<Wagon> wagons = wagonRepository.findAll();
        if (wagons == null) {
            return null;
        }

        for (Wagon wagon : wagons) {
            List<Seat> wagonAllSeats = new ArrayList<>(wagon.getSeats());
            List<SeatDTO> wagonAllSeatDTOS = getSeatsDto(wagonAllSeats);
            wagonDtos.add(new WagonDTO(wagon, wagonAllSeatDTOS));
        }
        return wagonDtos;
    }

    private List<SeatDTO> getSeatsDto(List<Seat> seats) {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for (Seat seat : seats) {
            seatDTOS.add(new SeatDTO(seat));
        }
        return seatDTOS;
    }

    private void addSeatOnNewWagon(Wagon wagon) {
        if (wagon == null) {
            return;
        }
        for (int i = 0; i < wagon.getWagonType().getNumberOfSeats(); i++) {
            Seat seat = new Seat(wagon, i + 1);
            seatRepository.saveAndFlush(seat);
        }

    }

}
