package com.app.bms.services;

import com.app.bms.models.dto.request.BookingRequestDTO;
import com.app.bms.models.entity.Payment;

public interface PaymentService {
    Payment initiatePayment(BookingRequestDTO bookingRequestDTO);
}
