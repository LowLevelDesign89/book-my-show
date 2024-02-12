package com.app.bms.controllers;

import com.app.bms.models.dto.request.BookingRequestDTO;
import com.app.bms.models.entity.Ticket;
import com.app.bms.services.BookingService;
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
    public ResponseEntity<Ticket> createTicket(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return new ResponseEntity<>(bookingService.bookTicketForShow(bookingRequestDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getBookingDetails(@PathVariable Long ticketId) {
        return new ResponseEntity<>(bookingService.getBookingDetails(ticketId),
                HttpStatus.OK);
    }
}
