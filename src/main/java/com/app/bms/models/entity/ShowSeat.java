package com.app.bms.models.entity;


import com.app.bms.models.enums.SeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@Data
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Show show;


    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

}
