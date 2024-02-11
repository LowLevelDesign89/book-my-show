package com.app.bms.models.entity;

import com.app.bms.models.enums.PaymentMethod;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class Payment extends BaseModel {
    private PaymentMethod paymentMethod;

    private BigDecimal totalPrice;
}
