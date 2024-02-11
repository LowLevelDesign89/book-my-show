package com.app.bms.services.impl;

import com.app.bms.exceptions.NotFoundException;
import com.app.bms.models.dto.request.HallRequestDTO;
import com.app.bms.models.dto.request.TheatreRequestDTO;
import com.app.bms.models.entity.Address;
import com.app.bms.models.entity.Hall;
import com.app.bms.models.entity.Seat;
import com.app.bms.models.entity.Theatre;
import com.app.bms.repositories.AddressRepository;
import com.app.bms.repositories.TheatreRepository;
import com.app.bms.services.AttributesBuilder;
import com.app.bms.services.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private AttributesBuilder attributesBuilder;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Theatre createTheatre(TheatreRequestDTO theatreRequestDTO) {
        Address address = theatreRequestDTO.getAddress();
        if(address.getId() == null) {
            address.setCrearedAt(ZonedDateTime.now());
            address.setLastUpdatedAt(ZonedDateTime.now());
            addressRepository.save(address);
        }

        Theatre theatre = new Theatre();
        theatre.setName(theatreRequestDTO.getName());
        theatre.setAddress(address);
        theatre.setCrearedAt(ZonedDateTime.now());
        theatre.setLastUpdatedAt(ZonedDateTime.now());

        List<Hall> movieHalls = new ArrayList<>();

        for(HallRequestDTO hallRequestDTO: theatreRequestDTO.getHalls()) {
            Hall hall = new Hall().builder()
                    .name(hallRequestDTO.getName())
                    .movieFeatures(hallRequestDTO.getFeatureList())
                    .crearedAt(ZonedDateTime.now())
                    .lastUpdatedAt(ZonedDateTime.now())
                    .build();

            List<Seat> seats = attributesBuilder.buildMovieSeats(hallRequestDTO.getNumSeatRows(), hallRequestDTO.getNumSeatCols());
            hall.setSeats(seats);
            movieHalls.add(hall);
        }
        theatre.setMovieHalls(movieHalls);
        return theatreRepository.save(theatre);
    }

    @Override
    public Theatre getTheatreDetails(Long theatreId) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if(theatreOptional.isEmpty()) {
            log.error("Theatre with ID: {} not exists in the system", theatreId);
            throw new NotFoundException("Theatre with ID " + theatreId + " does not exist in the system");
        }
        return theatreOptional.get();
    }
}
