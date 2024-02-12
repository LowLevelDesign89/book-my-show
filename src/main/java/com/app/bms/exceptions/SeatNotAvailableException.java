package com.app.bms.exceptions;

import com.app.bms.utils.MessageConstants;

public class SeatNotAvailableException extends RuntimeException {


    public SeatNotAvailableException() {
        super(MessageConstants.SEAT_NOT_AVAILABLE);
    }
}
