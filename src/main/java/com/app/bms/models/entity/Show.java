package com.app.bms.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "shows")
@Data
@NoArgsConstructor
@SuperBuilder
public class Show extends BaseModel{
    @ManyToOne
    private Hall hall;

    @ManyToOne
    private Movie movie;

    private long startTime;

    private long duration;
}
