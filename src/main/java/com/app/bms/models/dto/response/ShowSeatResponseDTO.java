package com.app.bms.models.dto.response;

import com.app.bms.models.enums.SeatStatus;
import lombok.Data;

@Data
public class ShowSeatResponseDTO {
    private Long seatId;
    private int rowNum;
    private int colNum;
    private SeatStatus seatStatus;
}
