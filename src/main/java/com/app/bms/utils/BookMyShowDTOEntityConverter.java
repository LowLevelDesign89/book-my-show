package com.app.bms.utils;

import com.app.bms.models.dto.response.ShowResponseDTO;
import com.app.bms.models.dto.response.ShowSeatResponseDTO;
import com.app.bms.models.dto.response.TicketResponseDTO;
import com.app.bms.models.entity.Show;
import com.app.bms.models.entity.ShowSeat;
import com.app.bms.models.entity.Ticket;

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

    public static TicketResponseDTO convertToTicketResponseDTO(Ticket ticket) {
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setTicketId(ticket.getId());
        ShowResponseDTO showResponseDTO = new ShowResponseDTO();
        Show show = ticket.getShowSeats().get(0).getShow();
        showResponseDTO.setMovie(show.getMovie());
        showResponseDTO.setShowId(show.getId());
        showResponseDTO.setDuration(show.getDuration());
        showResponseDTO.setStartTime(show.getStartTime());
        showResponseDTO.setHall(show.getHall());
        showResponseDTO.setShowSeats(convertToShowSeatResponseDTOs(ticket.getShowSeats()));
        ticketResponseDTO.setShow(showResponseDTO);
        return ticketResponseDTO;
    }



}
