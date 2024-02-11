package com.app.bms.models.entity;

import com.app.bms.models.enums.City;
import com.app.bms.models.enums.State;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
public class Address extends BaseModel {
    private String street;
    private City city;
    private State state;
    private String zipCode;
}
