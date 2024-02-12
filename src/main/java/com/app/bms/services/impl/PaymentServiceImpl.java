package com.app.bms.services.impl;

import com.app.bms.models.dto.request.BookingRequestDTO;
import com.app.bms.models.entity.Payment;
import com.app.bms.repositories.PaymentRepository;
import com.app.bms.services.PaymentService;
import com.app.bms.services.strategies.PricingStrategy;
import com.app.bms.utils.AppConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private Map<String, PricingStrategy> pricingStrategyMap;

    @Autowired
    private PaymentRepository paymentRepository;

    //we can make payment retriable
    @Override
    public Payment initiatePayment(BookingRequestDTO bookingRequestDTO) {
        log.info("Payment initiated for the booking: {}", bookingRequestDTO);

        Payment payment = new Payment();
        payment.setPaymentMethod(bookingRequestDTO.getPaymentMethod());

        PricingStrategy pricingStrategy = pricingStrategyMap.get(AppConstants.DEFAULT_PRICING_STRATEGY);
        payment.setTotalPrice(pricingStrategy.calculateTotalPrice(bookingRequestDTO.getNumOfSeats()));
        return paymentRepository.save(payment);
    }
}
