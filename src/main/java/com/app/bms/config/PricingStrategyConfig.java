package com.app.bms.config;

import com.app.bms.services.strategies.DefaultPricingStrategy;
import com.app.bms.services.strategies.PricingStrategy;
import com.app.bms.services.strategies.SeatTypeBasedPricingStrategy;
import com.app.bms.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PricingStrategyConfig {
    @Autowired
    private DefaultPricingStrategy defaultPricingStrategy;

    @Autowired
    private SeatTypeBasedPricingStrategy seatTypeBasedPricingStrategy;

    @Bean
    public Map<String, PricingStrategy> pricingStrategyMap() {
        Map<String, PricingStrategy> pricingStrategyMap = new HashMap<>();
        pricingStrategyMap.put(AppConstants.DEFAULT_PRICING_STRATEGY, defaultPricingStrategy);
        pricingStrategyMap.put(AppConstants.SEAT_TYPE_BASED_PRICING_STRATEGY, seatTypeBasedPricingStrategy);
        return pricingStrategyMap;
    }
}
