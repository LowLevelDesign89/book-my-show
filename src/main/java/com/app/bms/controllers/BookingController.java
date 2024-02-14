package com.app.bms.controllers;

import com.app.bms.models.dto.request.BookingRequestDTO;
import com.app.bms.models.dto.response.TicketResponseDTO;
import com.app.bms.models.entity.Ticket;
import com.app.bms.services.BookingService;
import com.app.bms.utils.BookMyShowDTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody BookingRequestDTO bookingRequestDTO) {
        Ticket ticket = bookingService.bookTicketForShow(bookingRequestDTO);
        TicketResponseDTO ticketResponseDTO = BookMyShowDTOEntityConverter.convertToTicketResponseDTO(ticket);
        return new ResponseEntity<>(ticketResponseDTO,
                HttpStatus.CREATED);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getBookingDetails(@PathVariable Long ticketId) {
        Ticket ticket = bookingService.getBookingDetails(ticketId);
        TicketResponseDTO ticketResponseDTO = BookMyShowDTOEntityConverter.convertToTicketResponseDTO(ticket);
        return new ResponseEntity<>(ticketResponseDTO,
                HttpStatus.OK);
    }
}
