package com.app.bms.models.dto.response;

import com.app.bms.models.entity.Hall;
import com.app.bms.models.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class ShowResponseDTO {
    private Long showId;
    private long startTime;
    private long duration;
    private Hall hall;
    private List<ShowSeatResponseDTO> showSeats;
    private Movie movie;
}
