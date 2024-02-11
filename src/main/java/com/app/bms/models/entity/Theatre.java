package com.app.bms.models.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
public class Theatre extends BaseModel {
    private String name;

    @OneToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Hall> movieHalls;
}
