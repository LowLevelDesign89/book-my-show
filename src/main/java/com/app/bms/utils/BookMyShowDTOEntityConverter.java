package com.app.bms.utils;

import com.app.bms.models.dto.response.ShowResponseDTO;
import com.app.bms.models.dto.response.ShowSeatResponseDTO;
import com.app.bms.models.entity.Show;
import com.app.bms.models.entity.ShowSeat;

import java.util.List;
import java.util.stream.Collectors;

public class BookMyShowDTOEntityConverter {
    public static ShowResponseDTO convertToResponseDTO(Show show) {
        ShowResponseDTO responseDTO = new ShowResponseDTO();
        responseDTO.setHall(show.getHall());
        responseDTO.setDuration(show.getDuration());
        responseDTO.setMovie(show.getMovie());
        responseDTO.setStartTime(show.getStartTime());
        responseDTO.setShowId(show.getId());
        return responseDTO;
    }

    public static List<ShowSeatResponseDTO> convertToShowSeatResponseDTOs(List<ShowSeat> showSeatList) {
        return showSeatList.stream()
                .map(BookMyShowDTOEntityConverter::convertToShowSeatResponseDTO)
                .collect(Collectors.toList());
    }

    public static ShowSeatResponseDTO convertToShowSeatResponseDTO(ShowSeat showSeat) {
        ShowSeatResponseDTO responseDTO = new ShowSeatResponseDTO();
        responseDTO.setSeatStatus(showSeat.getSeatStatus());
        responseDTO.setRowNum(showSeat.getSeat().getRowNum());
        responseDTO.setColNum(showSeat.getSeat().getColNum());
        responseDTO.setSeatId(showSeat.getSeat().getId());
        return responseDTO;
    }

}
