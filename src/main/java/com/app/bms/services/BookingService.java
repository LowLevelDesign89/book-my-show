package com.app.bms.services;

import com.app.bms.models.dto.request.BookingRequestDTO;
import com.app.bms.models.entity.Ticket;

public interface BookingService {
    Ticket bookTicketForShow(BookingRequestDTO bookingRequestDTO);

    Ticket getBookingDetails(Long ticketId);
}
