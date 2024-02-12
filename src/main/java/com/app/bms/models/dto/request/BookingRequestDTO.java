package com.app.bms.models.dto.request;

import com.app.bms.models.enums.PaymentMethod;
import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long showId;
    private int numOfSeats;
    private Long userId;
    private PaymentMethod paymentMethod;
}
