package com.app.bms.services.strategies;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculateTotalPrice(int totalNumberOfSeats);
}
