package com.app.bms.models.dto.response;


import com.app.bms.models.entity.Payment;
import lombok.Data;

@Data
public class TicketResponseDTO {
    private Long ticketId;
    private ShowResponseDTO show;
    private Payment payment;
}
