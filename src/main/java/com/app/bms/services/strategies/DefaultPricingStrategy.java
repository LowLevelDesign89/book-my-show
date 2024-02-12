package com.app.bms.services.strategies;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultPricingStrategy implements PricingStrategy{
    private final BigDecimal perSeatPrice = BigDecimal.valueOf(100);

    @Override
    public BigDecimal calculateTotalPrice(int totalNumberOfSeats) {
        return perSeatPrice.multiply(BigDecimal.valueOf(totalNumberOfSeats));
    }
}
