package com.app.bms.services;

import com.app.bms.models.dto.request.HallRequestDTO;
import com.app.bms.models.entity.Hall;
import com.app.bms.models.entity.Seat;
import com.app.bms.models.entity.Show;
import com.app.bms.models.entity.ShowSeat;
import com.app.bms.models.enums.SeatStatus;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttributesBuilder {
    public List<Seat> buildMovieSeats(int numSeatRows, int numSeatCols) {
        List<Seat> seats = new ArrayList<>();
        for(int row = 1; row <= numSeatRows; row++) {
            for(int col = 1; col <= numSeatCols; col++) {
                seats.add(buildMovieSeat(row, col));
            }
        }
        return seats;
    }

    public Seat buildMovieSeat(int seatRow, int seatCol) {
        return Seat.builder()
                .rowNum(seatRow)
                .colNum(seatCol)
                .crearedAt(ZonedDateTime.now())
                .lastUpdatedAt(ZonedDateTime.now())
                .build();
    }

    public List<ShowSeat> createShowSeats(Hall hall, Show show) {
        return (List<ShowSeat>) hall.getSeats()
                .stream().map(seat -> ShowSeat.builder()
                        .seat(seat)
                        .show(show)
                        .seatStatus(SeatStatus.AVAILABLE).build()).toList();
    }




}
