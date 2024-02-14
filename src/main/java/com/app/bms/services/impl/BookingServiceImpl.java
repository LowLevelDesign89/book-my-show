package com.app.bms.services.impl;

import com.app.bms.exceptions.NotFoundException;
import com.app.bms.exceptions.SeatNotAvailableException;
import com.app.bms.models.dto.request.BookingRequestDTO;
import com.app.bms.models.entity.*;
import com.app.bms.models.enums.SeatStatus;
import com.app.bms.repositories.ShowRepository;
import com.app.bms.repositories.ShowSeatRepository;
import com.app.bms.repositories.TicketRepository;
import com.app.bms.services.BookingService;
import com.app.bms.services.PaymentService;
import com.app.bms.services.UserService;
import com.app.bms.utils.BookMyShowDTOEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService  {
    @Autowired
    private UserService userService;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentService paymentService;

    @Override
    public Ticket bookTicketForShow(BookingRequestDTO bookingRequestDTO) {
        log.info("Started creating booking for the request: {}", bookingRequestDTO);
        User user = userService.getUserById(bookingRequestDTO.getUserId());

        Optional<Show> showOptional = showRepository.findById(bookingRequestDTO.getShowId());
        if(showOptional.isEmpty()) {
            log.error("Show with ID: {} does not exist in the system ", bookingRequestDTO.getShowId());
            throw new NotFoundException("Show with ID " + bookingRequestDTO.getShowId() + " not found in the system");
        }

        List<ShowSeat> showSeats = showSeatRepository.findByShowAndSeatStatus(showOptional.get(), SeatStatus.AVAILABLE);
        if(showSeats.isEmpty() || bookingRequestDTO.getNumOfSeats() > showSeats.size()) {
            log.error("Enough seat not available for the show : {}", bookingRequestDTO.getShowId());
            throw new SeatNotAvailableException();
        }

        // mark those many seats as reserved
        List<ShowSeat> reservedSeats = new ArrayList<>();
        for(int i = 0; i < bookingRequestDTO.getNumOfSeats(); i++) {
            ShowSeat currentShowSeat = showSeats.get(i);
            currentShowSeat.setSeatStatus(SeatStatus.RESERVED);
            currentShowSeat.setLastUpdatedAt(ZonedDateTime.now());
            reservedSeats.add(currentShowSeat);

        }
        showSeatRepository.saveAll(reservedSeats);
        // call the payment service within try catch

        Payment payment = null;
        try {
            payment = paymentService.initiatePayment(bookingRequestDTO);
        } catch(Exception exception) {
            log.error("An error occurred while trying to perform the payment");
            updateSeatStatus(reservedSeats, SeatStatus.AVAILABLE);
            throw exception;
        }
        updateSeatStatus(reservedSeats, SeatStatus.BOOKED);
        Ticket ticket = new Ticket();
        ticket.setPayment(payment);
        ticket.setUser(user);
        ticket.setShowSeats(reservedSeats);
        ticket.setCrearedAt(ZonedDateTime.now());
        ticket.setLastUpdatedAt(ZonedDateTime.now());

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getBookingDetails(Long ticketId) {
        log.info("started fetching booking details for the ticketId: {}", ticketId);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()) {
            log.error("Ticket with ID: {} doesn't exist in the system", ticketId);
            throw new NotFoundException("Ticket with ID " + ticketId + " doesn't exist in the system");
        }
        return ticketOptional.get();
    }

    private void updateSeatStatus(List<ShowSeat> showSeats, SeatStatus seatStatus) {
        for(ShowSeat showSeat: showSeats) {
            showSeat.setSeatStatus(seatStatus);
        }
        showSeatRepository.saveAll(showSeats);
    }
}
