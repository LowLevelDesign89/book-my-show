package com.app.bms.services.strategies;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SeatTypeBasedPricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculateTotalPrice(int totalNumberOfSeats) {
        return null;
    }
}
