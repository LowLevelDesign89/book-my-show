package com.app.bms.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Ticket extends BaseModel{
    @OneToMany
    private List<ShowSeat> showSeats;

    @OneToOne
    private Payment payment;

    @ManyToOne
    private User user;
}
